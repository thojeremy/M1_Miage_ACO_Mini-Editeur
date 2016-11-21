package fr.istic.m1.aco.miniediteur.v3.UndoRedo;

public class UndoRedoMemento {
	private String etat;
	
	public UndoRedoMemento(String etat){
		this.etat = etat;
	}
	
	public String getEtat(){
		return etat;
	}
}
