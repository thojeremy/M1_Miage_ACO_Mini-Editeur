package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Selectionner implements Order{
	private MEImpl moteur;
	private int debut;
	private int fin;
	
	public Selectionner(MEImpl moteur){
		this.moteur = moteur;
		debut = 0;
		fin = 0;
	}
	
	public void setSelection(int debut, int fin){
		this.debut = debut;
		this.fin = fin;
	}
	
	@Override
	public void execute() {
		moteur.selectionnerDebut(debut);
		moteur.selectionnerFin(fin);
	}

}