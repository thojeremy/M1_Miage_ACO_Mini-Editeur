package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Supprimer implements Order{
	private MEImpl moteur;
	private int nbCaracteres;
	
	/**
	 * Constructeur de l'ordre Supprimer
	 * 
	 * @param moteur	Le moteur d'édition
	 */
	public Supprimer(MEImpl moteur){
		this.moteur = moteur;
		nbCaracteres = 0;
	}
	
	/**
	 * Permet de dire combien de caractères on souhaite supprimer
	 * 
	 * @param nbCaracteres	Le nombre de caractères
	 */
	public void setNbCaracteres(int nbCaracteres){
		this.nbCaracteres = nbCaracteres;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.command.Order#execute()
	 */
	@Override
	public void execute() {
		moteur.supprimerTexte(nbCaracteres);
	}

}
