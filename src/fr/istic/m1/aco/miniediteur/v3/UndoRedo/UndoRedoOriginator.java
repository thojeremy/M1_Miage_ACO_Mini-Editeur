package fr.istic.m1.aco.miniediteur.v3.UndoRedo;

public class UndoRedoOriginator {
	private String etat;
	
	/**
	 * Constructeur de UndoRedoOriginator
	 */
	public UndoRedoOriginator(){
		etat = "";
	}
	
	/**
	 * Permet de mettre une nouvelle cha�ne de caract�res � l'originator
	 * 
	 * @param etat	La nouvelle chaine de caract�res
	 */
	public void setEtat(String etat){
		this.etat = etat;
	}
	
	/**
	 * Permet de prendre la cha�ne de caract�res courante dans l'originator
	 * 
	 * @return	La cha�ne de caract�res courante dans l'originator
	 */
	public String getEtat(){
		return etat;
	}
	
	/**
	 * Permet d'exporter l'originator dans un memento
	 * 
	 * @return	Le memento g�n�r�
	 */
	public UndoRedoMemento toMemento(){
		return new UndoRedoMemento(etat);
	}
	
	/**
	 * Permet d'importer un memento
	 * 
	 * @param memento	Le memento � importer
	 */
	public void fromMemento(UndoRedoMemento memento){
		etat = memento.getEtat();
	}
}
