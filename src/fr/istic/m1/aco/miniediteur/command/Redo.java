package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Redo implements Order{
	private MEImpl moteur;
	
	/**
	 * Constructeur de l'ordre Redo
	 * 
	 * @param moteur	Le moteur d'édition
	 */
	public Redo(MEImpl moteur){
		this.moteur = moteur;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.command.Order#execute()
	 */
	@Override
	public void execute() {
		moteur.redo();
	}

}
