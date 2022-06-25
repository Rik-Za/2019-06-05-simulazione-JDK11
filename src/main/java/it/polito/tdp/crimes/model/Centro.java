package it.polito.tdp.crimes.model;

import com.javadocmd.simplelatlng.LatLng;

public class Centro {
	private int quartiere;
	private LatLng centro;
	public Centro(int quartiere, LatLng centro) {
		super();
		this.quartiere = quartiere;
		this.centro = centro;
	}
	public int getQuartiere() {
		return quartiere;
	}
	public void setQuartiere(int quartiere) {
		this.quartiere = quartiere;
	}
	public LatLng getCentro() {
		return centro;
	}
	public void setCentro(LatLng centro) {
		this.centro = centro;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centro == null) ? 0 : centro.hashCode());
		result = prime * result + quartiere;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Centro other = (Centro) obj;
		if (centro == null) {
			if (other.centro != null)
				return false;
		} else if (!centro.equals(other.centro))
			return false;
		if (quartiere != other.quartiere)
			return false;
		return true;
	}
	
	

}
