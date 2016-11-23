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
	
	public MacrosMemento get(int index){
		if(mementoList.size() == 0 || index >= mementoList.size() || index < 0){
			throw new IndexOutOfBoundsException();
		}
		return mementoList.get(index);
	}
	
	public int size(){
		return mementoList.size();
	}
	
	public void clear(){
		mementoList.clear();
	}
}
