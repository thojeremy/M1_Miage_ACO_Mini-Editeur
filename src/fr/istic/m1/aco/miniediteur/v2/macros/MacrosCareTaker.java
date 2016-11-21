package fr.istic.m1.aco.miniediteur.v2.macros;

import java.util.ArrayList;
import java.util.List;

public class MacrosCareTaker {
	private List<MacrosMemento> mementoList;
	
	public MacrosCareTaker(){
		mementoList = new ArrayList<MacrosMemento>();
	}
	
	public void add(MacrosMemento memento){
		mementoList.add(memento);
	}
	
	public MacrosMemento get(){
		if(mementoList.size() == 0){
			throw new IndexOutOfBoundsException();
		}
		return mementoList.remove(0);
	}
	
	public int size(){
		return mementoList.size();
	}
	
	public void clear(){
		mementoList.clear();
	}
}
