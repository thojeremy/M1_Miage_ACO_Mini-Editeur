package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Curseur implements Order{
	private MEImpl moteur;
	private int position;
	
	public Curseur(MEImpl moteur){
		this.moteur = moteur;
		this.position = 0;
	}
	
	public void setPosition(int position){
		this.position = position;
	}
	
	@Override
	public void execute() {
		moteur.setCurseur(position);
	}

}