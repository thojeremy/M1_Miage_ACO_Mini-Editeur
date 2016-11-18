package fr.istic.m1.aco.miniediteur.main.v3;

public class Memento {
	private String etat;
	
	public Memento(String etat){
		this.etat = etat;
	}
	
	public String getEtat(){
		return etat;
	}
}
