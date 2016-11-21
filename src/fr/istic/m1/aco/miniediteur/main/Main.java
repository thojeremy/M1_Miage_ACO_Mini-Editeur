package fr.istic.m1.aco.miniediteur.main;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class Main{
	
	public static MEImpl moteur;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialisation du broker
		moteur = new MEImpl();
		
		Fenetre fen = new Fenetre();
		fen.setVisible(true);
	}
}
