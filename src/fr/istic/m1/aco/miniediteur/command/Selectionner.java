package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Selectionner implements Order{
	private MEImpl moteur;
	private int debut;
	private int fin;
	
	public Selectionner(MEImpl moteur){
		this.moteur = moteur;
		this.debut = 0;
		this.fin = 0;
	}
	
	public void setSelectionDebut(int debut){
		this.debut = debut;
	}
	
	public void setSelectionFin(int fin){
		this.fin = fin;
	}
	
	@Override
	public void execute() {
		moteur.selectionnerDebut(debut);
		moteur.selectionnerFin(fin);
	}

}