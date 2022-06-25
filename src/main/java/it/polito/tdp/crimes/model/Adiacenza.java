package it.polito.tdp.crimes.model;

public class Adiacenza implements Comparable<Adiacenza> {
	private int q;
	private double peso;
	public Adiacenza(int q, double peso) {
		super();
		this.q = q;
		this.peso = peso;
	}
	public int getQ() {
		return q;
	}
	public void setQ(int q) {
		this.q = q;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(Adiacenza o) {
		return Double.compare(this.peso, o.peso);
	}
	@Override
	public String toString() {
		return "Quartiere: "+ q + ", distanza: " + peso;
	}
	
	
	

}
