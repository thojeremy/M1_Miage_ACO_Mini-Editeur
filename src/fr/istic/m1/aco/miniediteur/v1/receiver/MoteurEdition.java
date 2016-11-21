package fr.istic.m1.aco.miniediteur.v1.receiver;

public interface MoteurEdition {
	public void couper();
	public void copier();
	public void coller();
	public void insererTexte(String texte);
	public void supprimerTexte(int nombre);
	public void selectionnerDebut(int debut);
	public void selectionnerFin(int fin);
	
	public String getBuffer();
	public String getPressePapier();
}
