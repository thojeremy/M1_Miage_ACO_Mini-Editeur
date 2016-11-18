package fr.istic.m1.aco.miniediteur.v3.memento;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
	private List<Memento> mementoList;
	
	public CareTaker(){
		mementoList = new ArrayList<Memento>();
	}
	
	public void add(Memento memento){
		mementoList.add(memento);
	}
	
	public Memento get(){
		if(mementoList.size() == 0){
			throw new IndexOutOfBoundsException();
		}
		return mementoList.remove(mementoList.size() - 1);
	}
	
	public int size(){
		return mementoList.size();
	}
	
	public void clear(){
		mementoList.clear();
	}
}
