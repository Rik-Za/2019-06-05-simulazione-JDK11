package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	private EventsDao dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private List<Centro> archi;
	
	public Model() {
		this.dao= new EventsDao();
		this.archi= new ArrayList<>();
	}
	
	public String creaGrafo(int anno) {
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		//Aggiungo vertici
		Graphs.addAllVertices(this.grafo, this.dao.getVertici());
		//Aggiungo archi
		archi=this.dao.getArchi(anno);
		for(Centro c1: archi) {
			for(Centro c2: archi)
				if(!c1.equals(c2)) {
					double peso=LatLngTool.distance(c1.getCentro(), c2.getCentro(), LengthUnit.KILOMETER);
					Graphs.addEdge(this.grafo, c1.getQuartiere(), c2.getQuartiere(),peso);	
				}
		}
		String s="GRAFO CREATO!\n";
		s+="#VERTICI: "+this.grafo.vertexSet().size()+"\n";
		s+="#ARCHI: "+this.grafo.edgeSet().size()+"\n";
		return s;
	}
	
	public String getVicini(){
		String risultato="";
		for(Integer i: this.grafo.vertexSet()) {
			risultato+="Vicini del quartiere "+i+":\n";
			List<Adiacenza> vicini = new ArrayList<Adiacenza>();
			for(Integer v: Graphs.neighborListOf(this.grafo, i)) {
				DefaultWeightedEdge e = this.grafo.getEdge(i, v);
				Adiacenza a = new Adiacenza(v, this.grafo.getEdgeWeight(e));
				vicini.add(a);
			}
			Collections.sort(vicini);
			for(Adiacenza aa: vicini)
				risultato+=aa.toString()+"\n";
		}
		return risultato;
	}
	
	public int simula(int num, int giorno, int mese, int anno) {
		Simulatore sim = new Simulatore(grafo);
		sim.init(num, mese, giorno, anno);
		int ris = sim.run();
		return ris;
	}
	

	
}
