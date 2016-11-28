package fr.istic.m1.aco.miniediteur.v2.macros;

import fr.istic.m1.aco.miniediteur.command.Order;
import fr.istic.m1.aco.miniediteur.v3.UndoRedo.UndoRedoMemento;

public class MacrosOriginator {
	private Order etat;
	
	/**
	 * Constructeur de la classe MacrosOriginator
	 */
	public MacrosOriginator(){
		etat = null;
	}
	
	/**
	 * Permet de mettre un ordre dans l'originator
	 * 
	 * @param etat	L'ordre à assigner à l'originator
	 */
	public void setEtat(Order etat){
		this.etat = etat;
	}
	
	/**
	 * Permet de prendre l'ordre courant de l'originator
	 * 
	 * @return	L'ordre courant de l'originator
	 */
	public Order getEtat(){
		return etat;
	}
	
	/**
	 * Permet de convertir l'ordre courant en Memento
	 * 
	 * @return	Le memento de l'ordre courant
	 */
	public MacrosMemento toMemento(){
		return new MacrosMemento(etat);
	}
	
	/**
	 * Permet d'importer un memento et le stocker dans l'originator
	 * 
	 * @param memento	Le memento à importer
	 */
	public void fromMemento(MacrosMemento memento){
		etat = memento.getEtat();
	}
}
