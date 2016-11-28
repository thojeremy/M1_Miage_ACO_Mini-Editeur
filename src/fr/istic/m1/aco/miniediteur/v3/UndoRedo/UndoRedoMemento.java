package fr.istic.m1.aco.miniediteur.v3.UndoRedo;

public class UndoRedoMemento {
	private String etat;
	
	/**
	 * Constructeur de la classe UndoRedoMemento
	 * 
	 * @param etat	La chaîne de caractères à mettre dans l'état courant
	 */
	public UndoRedoMemento(String etat){
		this.etat = etat;
	}
	
	/**
	 * Permet de prendre l'état courant du memento
	 * 
	 * @return	La chaîne caractères de l'état courant du memento
	 */
	public String getEtat(){
		return etat;
	}
}
