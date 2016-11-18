package fr.istic.m1.aco.miniediteur.main.v3;

public class Originator {
	private String etat;
	
	public void setEtat(String etat){
		this.etat = etat;
	}
	
	public String getEtat(){
		return etat;
	}
	
	public Memento toMemento(){
		return new Memento(etat);
	}
	
	public void fromMemento(Memento memento){
		etat = memento.getEtat();
	}
}
