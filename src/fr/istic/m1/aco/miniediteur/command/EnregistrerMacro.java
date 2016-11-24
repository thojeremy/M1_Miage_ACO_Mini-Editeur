package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class EnregistrerMacro implements Order{
	private MEImpl moteur;
	private Order order;
	
	public EnregistrerMacro(MEImpl moteur){
		this.moteur = moteur;
	}
	
	public void setOrder(Order order){
		this.order = order;
	}
	
	@Override
	public void execute() {
		moteur.ajouterMacro(order);
	}

}
