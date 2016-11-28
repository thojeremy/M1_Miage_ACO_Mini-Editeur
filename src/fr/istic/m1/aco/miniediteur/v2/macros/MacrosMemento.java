package fr.istic.m1.aco.miniediteur.v2.macros;

import fr.istic.m1.aco.miniediteur.command.Order;

public class MacrosMemento {
	private Order etat;
	
	/**
	 * Constructeur de la classe MacrosMemento
	 * 
	 * @param etat	L'ordre courant du memento
	 */
	public MacrosMemento(Order etat){
		this.etat = etat;
	}
	
	/**
	 * Permet de prendre l'ordre courant du memento
	 * 
	 * @return	L'ordre courant du memento
	 */
	public Order getEtat(){
		return etat;
	}
}
