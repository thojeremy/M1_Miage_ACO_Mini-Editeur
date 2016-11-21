package fr.istic.m1.aco.miniediteur.v2.macros;

import fr.istic.m1.aco.miniediteur.command.Order;

public class MacrosMemento {
	private Order etat;
	
	public MacrosMemento(Order etat){
		this.etat = etat;
	}
	
	public Order getEtat(){
		return etat;
	}
}
