package fr.istic.m1.aco.miniediteur.v2.macros;

import java.util.ArrayList;
import java.util.List;

public class MacrosCareTaker {
	private List<MacrosMemento> mementoList;
	
	/**
	 * Constructeur de la classe MacrosCareTaker
	 */
	public MacrosCareTaker(){
		mementoList = new ArrayList<MacrosMemento>();
	}
	
	/**
	 * Permet d'ajouter un memento au care taker
	 * 
	 * @param memento	Le memento à ajouter
	 */
	public void add(MacrosMemento memento){
		mementoList.add(memento);
	}
	
	/**
	 * Permet de retourner le memento à l'indice [index]
	 * 
	 * @param index		L'indice voulu
	 * 
	 * @return			Le Memento correspondant à l'indice voulu
	 */
	public MacrosMemento get(int index){
		if(mementoList.size() == 0 || index >= mementoList.size() || index < 0){
			throw new IndexOutOfBoundsException();
		}
		return mementoList.get(index);
	}
	
	/**
	 * Permet de prendre la taille de la liste de mementos
	 * 
	 * @return	La taille de la liste de mementos
	 */
	public int size(){
		return mementoList.size();
	}
	
	/**
	 * Permet de vider la liste de mementos
	 */
	public void clear(){
		mementoList.clear();
	}
}
