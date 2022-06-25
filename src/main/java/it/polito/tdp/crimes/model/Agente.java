package it.polito.tdp.crimes.model;


public class Agente {
		private boolean occupato;
		private int tempo;
		private int id;
		public Agente(int id) {
			super();
			this.occupato = false;
			this.id = id;
		}
		public boolean isOccupato() {
			return occupato;
		}
		public void setOccupato(boolean occupato) {
			this.occupato = occupato;
		}
		public int getTempo() {
			return tempo;
		}
		public void setTempo(int tempo) {
			this.tempo += tempo;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
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
			Agente other = (Agente) obj;
			if (id != other.id)
				return false;
			return true;
		}
		
		
		
}
