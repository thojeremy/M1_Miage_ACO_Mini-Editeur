package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Inserer implements Order{
	private MEImpl moteur;
	private String texte;
	
	public Inserer(MEImpl moteur){
		this.moteur = moteur;
		this.texte = "";
	}
	
	public void setTexte(String texte){
		this.texte = texte;
	}

	@Override
	public void execute() {
		moteur.insererTexte(texte);
	}

}
