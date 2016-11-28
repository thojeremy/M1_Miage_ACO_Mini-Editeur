package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Inserer implements Order, Cloneable{
	private MEImpl moteur;
	private String texte;
	
	/**
	 * Constructeur de l'ordre Inserer
	 * 
	 * @param moteur	Le moteur d'édition
	 */
	public Inserer(MEImpl moteur){
		this.moteur = moteur;
		this.texte = "";
	}
	
	/**
	 * Permet de mettre le texte qui sera inséré dans le Buffer
	 * 
	 * @param texte		Le texte à insérer dans le Buffer
	 */
	public void setTexte(String texte){
		this.texte = texte;
	}

	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.command.Order#execute()
	 */
	@Override
	public void execute() {
		moteur.insererTexte(texte);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Inserer clone(){
		Inserer i = new Inserer(moteur);
		i.setTexte(texte);
		return i;
	}

}
