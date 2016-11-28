package fr.istic.m1.aco.miniediteur.v1.receiver;

public class Selection {
	private int debut;
	private int fin;

	/**
	 * Le constructeur de la classe Selection
	 */
	public Selection(){
		debut = fin = 0;
	}
  
	/**
	 * Permet de renseigner l'indice de début de la sélection
	 * 
	 * @param debut	L'indice de début
	 */
	public void setDebut(int debut) {
		this.debut = debut;
	}
	
	/**
	 * Permet de renseigner l'indice de fin de la sélection
	 * 
	 * @param fin	L'indice de fin
	 */
	public void setFin(int fin) {
		this.fin = fin;
	}
	
	/**
	 * Retourne l'indice de début de la sélection
	 * 
	 * @return	L'indice de début
	 */
	public int getDebut() {
		return debut;
	}
	
	/**
	 * Retourne l'indice de fin de la sélection
	 * 
	 * @return	L'indice de fin
	 */
	public int getFin() {
		return fin;
	}
	
	/**
	 * Permet d'avoir l'intervalle entre le début et la fin de la sélection
	 * 
	 * @return	Le nombre de caractères entre le début et la fin de la sélection
	 */
	public int getInterval() {
		return fin - debut;
	}
	
	/**
	 * Permet d'inverser l'indice de début et de fin si fin est inférieur à début
	 */
	public void gestionDebutFin(){
		if(debut > fin){
			int tmp = debut;
			debut = fin;
			fin = tmp;
		}
	}
	
	/**
	 * Permet de savoir si quelque chose est sélectionné
	 * 
	 * @return	TRUE	Si quelque chose est sélectionné
	 * 			FALSE	Sinon
	 */
	public boolean selectionne(){
		return (debut >= 0 && fin >= 0) && (debut != fin);
	}
}
