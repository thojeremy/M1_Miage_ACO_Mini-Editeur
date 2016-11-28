package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Selectionner implements Order, Cloneable{
	private MEImpl moteur;
	private int debut;
	private int fin;
	
	/**
	 * Constructeur de l'ordre Selectionner
	 * 
	 * @param moteur	Le moteur d'édition
	 */
	public Selectionner(MEImpl moteur){
		this.moteur = moteur;
		this.debut = 0;
		this.fin = 0;
	}
	
	/**
	 * Permet de dire quel est l'indice de début dans le Buffer
	 * 
	 * @param debut		L'indice de début
	 */
	public void setSelectionDebut(int debut){
		this.debut = debut;
	}
	
	/**
	 * Permet de dire quel est l'indice de fin dans le Buffer
	 * 
	 * @param fin		L'indice de fin
	 */
	public void setSelectionFin(int fin){
		this.fin = fin;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.command.Order#execute()
	 */
	@Override
	public void execute() {
		moteur.selectionnerDebut(debut);
		moteur.selectionnerFin(fin);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Selectionner clone(){
		Selectionner s = new Selectionner(moteur);
		s.setSelectionDebut(debut);
		s.setSelectionFin(fin);
		return s;
	}

}