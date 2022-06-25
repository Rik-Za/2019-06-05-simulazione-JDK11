package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.crimes.db.EventsDao;

public class Simulatore {
	//Modello
	private List<Event> crimini = new ArrayList<>();
	private Graph<Integer,DefaultWeightedEdge> grafo;
	private EventsDao dao;
	private Map<Integer,Agente> agentiList;
	
	//Parametri della simulazione
	private int agenti;
	private int idQuartiereMenoPericoloso;
	private int periodo;
	
	//Coda degli eventi
	private PriorityQueue<Evento> queue;
	
	//Output
	private int malGestiti;
	
	public Simulatore(Graph<Integer,DefaultWeightedEdge> grafo) {
		this.grafo=grafo;
		this.dao= new EventsDao();
		this.agentiList=new HashMap<>();
	}
	
	public void init(int numero, int mese, int giorno, int anno) {
		this.malGestiti=0;
		this.agenti=numero;
		this.periodo=0;
		this.queue= new PriorityQueue<Evento>();
		for(int i=0;i<this.agenti;i++)
			agentiList.put(i,new Agente(i));
		this.crimini=this.dao.getEventiWithDayMonth(mese, giorno,anno);
		this.idQuartiereMenoPericoloso=this.dao.getQuartiereMenoPericoloso(anno);
		//assegno i primi eventi
		for(Agente a: agentiList.values()) {
			Event crimine = this.crimini.get(0);
			this.crimini.remove(0);
			int durata=0;	//è in minuti!
			if(crimine.getOffense_category_id().compareTo("all_other_crimes")==0) {
				if(Math.random()<0.5)
					durata=60;
				else
					durata=120;
				
			}else {
				durata=120;
			}
			//controllo lo spostamento
			int quartiereCrimine=crimine.getDistrict_id();
			int spostamento=0;
			if(idQuartiereMenoPericoloso!=quartiereCrimine) {
				DefaultWeightedEdge edge = this.grafo.getEdge(idQuartiereMenoPericoloso, quartiereCrimine);
				spostamento = (int)(this.grafo.getEdgeWeight(edge));
			}
			//converto in minuti l'ora del crimine
			int minutoCrimine = crimine.getReported_date().getMinute()+crimine.getReported_date().getHour()*60;
			
			//guardo se l'agente è dentro i 15 minuti di ritardo
			if(a.getTempo()+spostamento>minutoCrimine+15) {
				malGestiti++;
			}
			Evento e = new Evento(crimine, durata, periodo, a);
			periodo++;
			this.queue.add(e);	
			
		}
	}
	
	public int run() {
		while(!this.queue.isEmpty()) {
			Evento e = this.queue.poll();
			processEvent(e);
		}
		return malGestiti;
	}

	private void processEvent(Evento e) {
		Agente a= agentiList.get(e.getAgenteAssegnato().getId());
		a.setTempo(e.getDurata());
		
		//controllo ci siano altri crimini
		if(!this.crimini.isEmpty()) {
			
			//assegna nuovo crimine
			Event crimine = this.crimini.get(0);
			this.crimini.remove(0);
			
			//controllo il quartiere per calcolare lo spostamento
			int quartiereAgente= e.getCrimine().getDistrict_id();
			int quartiereCrimine= crimine.getDistrict_id();
			int spostamento=0;
			if(quartiereAgente!=quartiereCrimine) {
				DefaultWeightedEdge edge = this.grafo.getEdge(quartiereAgente, quartiereCrimine);
				spostamento = (int)(this.grafo.getEdgeWeight(edge));
			}
			//converto in minuti l'ora del crimine
			int minutoCrimine = crimine.getReported_date().getMinute()+crimine.getReported_date().getHour()*60;
			
			//guardo se l'agente è dentro i 15 minuti di ritardo
			if(a.getTempo()+spostamento>minutoCrimine+15) {
				malGestiti++;
			}
			//calcolo durata
			int durata=0;
			if(crimine.getOffense_category_id().compareTo("all_other_crimes")==0) {
				if(Math.random()<0.5)
					durata=60;
				else
					durata=120;
				
			}else {
				durata=120;
			}
			//assegno crimine e creo evento
			Evento evento = new Evento(crimine, durata+spostamento, periodo, a);
			this.queue.add(evento);
			periodo++;
		}
		
	}
	

}
