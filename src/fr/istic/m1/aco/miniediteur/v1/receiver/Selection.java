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
	 * Permet de renseigner l'indice de d�but de la s�lection
	 * 
	 * @param debut	L'indice de d�but
	 */
	public void setDebut(int debut) {
		this.debut = debut;
	}
	
	/**
	 * Permet de renseigner l'indice de fin de la s�lection
	 * 
	 * @param fin	L'indice de fin
	 */
	public void setFin(int fin) {
		this.fin = fin;
	}
	
	/**
	 * Retourne l'indice de d�but de la s�lection
	 * 
	 * @return	L'indice de d�but
	 */
	public int getDebut() {
		return debut;
	}
	
	/**
	 * Retourne l'indice de fin de la s�lection
	 * 
	 * @return	L'indice de fin
	 */
	public int getFin() {
		return fin;
	}
	
	/**
	 * Permet d'avoir l'intervalle entre le d�but et la fin de la s�lection
	 * 
	 * @return	Le nombre de caract�res entre le d�but et la fin de la s�lection
	 */
	public int getInterval() {
		return fin - debut;
	}
	
	/**
	 * Permet d'inverser l'indice de d�but et de fin si fin est inf�rieur � d�but
	 */
	public void gestionDebutFin(){
		if(debut > fin){
			int tmp = debut;
			debut = fin;
			fin = tmp;
		}
	}
	
	/**
	 * Permet de savoir si quelque chose est s�lectionn�
	 * 
	 * @return	TRUE	Si quelque chose est s�lectionn�
	 * 			FALSE	Sinon
	 */
	public boolean selectionne(){
		return (debut >= 0 && fin >= 0) && (debut != fin);
	}
}
