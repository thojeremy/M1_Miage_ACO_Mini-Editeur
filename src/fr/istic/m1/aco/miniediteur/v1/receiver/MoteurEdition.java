package fr.istic.m1.aco.miniediteur.v1.receiver;

import fr.istic.m1.aco.miniediteur.command.Order;

public interface MoteurEdition {
	// V1
	/**
	 * Permet de couper le texte
	 */
	public void couper();
	
	/**
	 * Permet de copier le texte
	 */
	public void copier();
	
	/**
	 * Permet de coller le texet
	 */
	public void coller();
	
	/**
	 * Permet d'insérer un texte au Buffer
	 * 
	 * @param texte	Le texte à ajouter
	 */
	public void insererTexte(String texte);
	
	/**
	 * Permet de supprimer [nombre] caractères
	 * 
	 * @param nombre	Le nombre de caractères à supprimer
	 */
	public void supprimerTexte(int nombre);
	
	/**
	 * Permet de définir l'indice de début de sélection
	 * 
	 * @param debut	L'indice de début de la sélection
	 */
	public void selectionnerDebut(int debut);
	
	/**
	 * Permet de définir l'indice de fin de sélection
	 * 
	 * @param fin	L'indice de fin de la sélection
	 */
	public void selectionnerFin(int fin);
	
	/**
	 * Permet de prendre le contenu courant du buffer
	 * 
	 * @return	Le contenu du buffer
	 */
	public String getBuffer();
	
	/**
	 * Permet de prendre le contenu courant du presse papier
	 * 
	 * @return	Le contenu du presse papier
	 */
	public String getPressePapier();
	
	/**
	 * Permet de supprimer [nombre] caractères vers la droite
	 * 
	 * @param nombre	Le nombre de caractères à supprimer
	 */
	public void supprimerTexteDel(int nombre);
	
	/**
	 * Permet de changer la position du curseur
	 * 
	 * @param position	La nouvelle position
	 */
	public void setCurseur(int position);
	
	/**
	 * Permet de prendre la position courante du curseur
	 * 
	 * @return	La position du curseur
	 */
	public int getCurseur();
	
	// V2
	/**
	 * Permet d'ajouter un ordre aux macros
	 * 
	 * @param order	L'ordre à ajouter
	 */
	public void ajouterMacro(Order order);
	
	/**
	 * Permet de changer l'état de l'enregistrement de la macro
	 */
	public void changerEtatEnregistrementMacro();
	
	/**
	 * Permet de jouer la macro
	 */
	public void jouerMacro();
	
	// V3
	/**
	 * Permet d'appliquer la commande undo:
	 * - On enlève l'état du buffer présent dans le undo
	 * - On l'exécute
	 * - On le met dans le redo
	 */
	public void undo();
	
	/**
	 * Permet d'appliquer la commande redo:
	 * - On enlève l'état du buffer présent dans le redo
	 * - On l'exécute
	 * - On le met dans le undo
	 */
	public void redo();
}
