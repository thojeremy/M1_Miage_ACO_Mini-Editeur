package fr.istic.m1.aco.miniediteur.v1.receiver;

public class Selection {
	private int debut;
	private int fin;

	public Selection(){
		debut = fin = 0;
	}
  
	public void setDebut(int debut) {
		this.debut = debut;
	}
	
	public void setFin(int fin) {
		this.fin = fin;
	}
	
	public int getDebut() {
		return debut;
	}
	
	public int getFin() {
		return fin;
	}
	
	public int getInterval() {
		return fin - debut;
	}
	
	public boolean selectionne(){
		return (debut != 0 && fin != 0) && (debut != fin);
	}
}
