package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Del implements Order{
	private MEImpl moteur;
	private int nbCaracteres;
	
	public Del(MEImpl moteur){
		this.moteur = moteur;
		nbCaracteres = 0;
	}
	
	public void setNbCaracteres(int nbCaracteres){
		this.nbCaracteres = nbCaracteres;
	}
	
	@Override
	public void execute() {
		moteur.supprimerTexteDel(nbCaracteres);
	}
}
