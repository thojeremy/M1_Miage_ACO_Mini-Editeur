package fr.istic.m1.aco.miniediteur.v2.macros;

import fr.istic.m1.aco.miniediteur.command.Order;
import fr.istic.m1.aco.miniediteur.v3.UndoRedo.UndoRedoMemento;

public class MacrosOriginator {
	private Order etat;
	
	public MacrosOriginator(){
		etat = null;
	}
	
	public void setEtat(Order etat){
		this.etat = etat;
	}
	
	public Order getEtat(){
		return etat;
	}
	
	public MacrosMemento toMemento(){
		return new MacrosMemento(etat);
	}
	
	public void fromMemento(MacrosMemento memento){
		etat = memento.getEtat();
	}
}
