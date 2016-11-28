package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Undo implements Order {
	private MEImpl moteur;
	
	/**
	 * Constructeur de l'ordre Undo
	 * 
	 * @param moteur	Le moteur d'édition
	 */
	public Undo(MEImpl moteur){
		this.moteur = moteur;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.command.Order#execute()
	 */
	@Override
	public void execute() {
		moteur.undo();
	}
}
