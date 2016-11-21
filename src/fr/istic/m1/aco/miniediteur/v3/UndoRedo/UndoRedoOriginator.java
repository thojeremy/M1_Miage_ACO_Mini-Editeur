package fr.istic.m1.aco.miniediteur.v3.UndoRedo;

public class UndoRedoOriginator {
	private String etat;
	
	public UndoRedoOriginator(){
		etat = "";
	}
	
	public void setEtat(String etat){
		this.etat = etat;
	}
	
	public String getEtat(){
		return etat;
	}
	
	public UndoRedoMemento toMemento(){
		return new UndoRedoMemento(etat);
	}
	
	public void fromMemento(UndoRedoMemento memento){
		etat = memento.getEtat();
	}
}
