package fr.istic.m1.aco.miniediteur.v3.UndoRedo;

import java.util.ArrayList;
import java.util.List;

public class UndoRedoCareTaker {
	private List<UndoRedoMemento> mementoList;
	
	public UndoRedoCareTaker(){
		mementoList = new ArrayList<UndoRedoMemento>();
	}
	
	public void add(UndoRedoMemento memento){
		mementoList.add(memento);
	}
	
	public UndoRedoMemento get(){
		if(mementoList.size() == 0){
			throw new IndexOutOfBoundsException();
		}
		return mementoList.remove(mementoList.size() - 1);
	}
	
	public int size(){
		return mementoList.size();
	}
	
	public boolean isEmpty(){
		return mementoList.size() == 0;
	}
	
	public void clear(){
		mementoList.clear();
	}
}
