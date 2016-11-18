package fr.istic.m1.aco.miniediteur.v3.memento;

public class Memento {
	private String etat;
	
	public Memento(String etat){
		this.etat = etat;
	}
	
	public String getEtat(){
		return etat;
	}
}
