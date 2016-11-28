package fr.istic.m1.aco.miniediteur.v3.UndoRedo;

public class UndoRedoMemento {
	private String etat;
	
	/**
	 * Constructeur de la classe UndoRedoMemento
	 * 
	 * @param etat	La cha�ne de caract�res � mettre dans l'�tat courant
	 */
	public UndoRedoMemento(String etat){
		this.etat = etat;
	}
	
	/**
	 * Permet de prendre l'�tat courant du memento
	 * 
	 * @return	La cha�ne caract�res de l'�tat courant du memento
	 */
	public String getEtat(){
		return etat;
	}
}
