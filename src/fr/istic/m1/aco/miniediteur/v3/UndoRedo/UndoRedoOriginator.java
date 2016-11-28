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
	 * Permet de mettre une nouvelle chaîne de caractères à l'originator
	 * 
	 * @param etat	La nouvelle chaine de caractères
	 */
	public void setEtat(String etat){
		this.etat = etat;
	}
	
	/**
	 * Permet de prendre la chaîne de caractères courante dans l'originator
	 * 
	 * @return	La chaîne de caractères courante dans l'originator
	 */
	public String getEtat(){
		return etat;
	}
	
	/**
	 * Permet d'exporter l'originator dans un memento
	 * 
	 * @return	Le memento généré
	 */
	public UndoRedoMemento toMemento(){
		return new UndoRedoMemento(etat);
	}
	
	/**
	 * Permet d'importer un memento
	 * 
	 * @param memento	Le memento à importer
	 */
	public void fromMemento(UndoRedoMemento memento){
		etat = memento.getEtat();
	}
}
