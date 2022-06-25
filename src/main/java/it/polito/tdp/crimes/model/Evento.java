package it.polito.tdp.crimes.model;


public class Evento implements Comparable<Evento> {
	private Event crimine;
	private int durata;
	private int time;
	private Agente agenteAssegnato;
	
	public Evento(Event crimine, int durata, int time, Agente agenteAssegnato) {
		super();
		this.crimine = crimine;
		this.durata = durata;
		this.time = time;
		this.agenteAssegnato = agenteAssegnato;
	}
	public Event getCrimine() {
		return crimine;
	}
	public void setCrimine(Event crimine) {
		this.crimine = crimine;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Agente getAgenteAssegnato() {
		return agenteAssegnato;
	}
	public void setAgenteAssegnato(Agente agenteAssegnato) {
		this.agenteAssegnato = agenteAssegnato;
	}
	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.time-o.time;
	}
}
	
	