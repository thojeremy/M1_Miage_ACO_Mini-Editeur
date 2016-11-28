package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Curseur implements Order, Cloneable{
	private MEImpl moteur;
	private int position;
	
	/**
	 * Constructeur de l'ordre Curseur
	 * 
	 * @param moteur	Le moteur d'édition
	 */
	public Curseur(MEImpl moteur){
		this.moteur = moteur;
		this.position = 0;
	}
	
	/**
	 * Renseigne la position du curseur
	 * 
	 * @param position	La nouvelle position du curseur
	 */
	public void setPosition(int position){
		this.position = position;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.command.Order#execute()
	 */
	@Override
	public void execute() {
		moteur.setCurseur(position);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Curseur clone(){
		Curseur c = new Curseur(moteur);
		c.setPosition(position);
		return c;
	}

}