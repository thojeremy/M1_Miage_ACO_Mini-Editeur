package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class EnregistrerMacro implements Order{
	private MEImpl moteur;
	private Order order;
	
	/**
	 * Constructeur de l'ordre EnregistrerMacro
	 * 
	 * @param moteur	Le moteur d'édition
	 */
	public EnregistrerMacro(MEImpl moteur){
		this.moteur = moteur;
	}
	
	/**
	 * Permet de mettre l'ordre à enregistrer dans la macro
	 * 
	 * @param order		L'ordre a enregistrer
	 */
	public void setOrder(Order order){
		this.order = order;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.command.Order#execute()
	 */
	@Override
	public void execute() {
		moteur.ajouterMacro(order);
	}

}
