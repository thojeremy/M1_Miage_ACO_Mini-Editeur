package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Copier implements Order{
	private MEImpl moteur;
	
	public Copier(MEImpl moteur){
		this.moteur = moteur;
	}
	
	@Override
	public void execute() {
		moteur.copier();
	}

}