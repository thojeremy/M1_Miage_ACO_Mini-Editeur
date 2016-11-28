package fr.istic.m1.aco.miniediteur.v3.UndoRedo;

import java.util.ArrayList;
import java.util.List;

public class UndoRedoCareTaker {
	private List<UndoRedoMemento> mementoList;
	
	/**
	 * Constructeur de la classe UndoRedoCareTaker
	 */
	public UndoRedoCareTaker(){
		mementoList = new ArrayList<UndoRedoMemento>();
	}
	
	/**
	 * Permet d'ajouter un memento à la liste des mementos
	 * 
	 * @param memento	Le memento à ajouter
	 */
	public void add(UndoRedoMemento memento){
		mementoList.add(memento);
	}
	
	/**
	 * Permet de prendre le dernier élément de la liste des mementos:
	 * - On le prend
	 * - On le supprime
	 * - On le retourne
	 * 
	 * La liste des mementos est sous la forme d'une liste LIFO
	 * 
	 * @return	Le dernier élément de la liste des mementos
	 */
	public UndoRedoMemento get(){
		if(mementoList.size() == 0){
			throw new IndexOutOfBoundsException();
		}
		return mementoList.remove(mementoList.size() - 1);
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
	 * Permet de tester si la liste de mementos est vide
	 * 
	 * @return	TRUE	Si elle la liste de mementos est vide
	 * 			FALSE	Sinon
	 */
	public boolean isEmpty(){
		return mementoList.size() == 0;
	}
	
	/**
	 * Permet de vider la liste de mementos
	 */
	public void clear(){
		mementoList.clear();
	}
}
