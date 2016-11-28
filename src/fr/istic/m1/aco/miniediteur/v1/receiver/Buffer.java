package fr.istic.m1.aco.miniediteur.v1.receiver;

public class Buffer {
	private StringBuffer zoneTexte;
  	
  	/**
  	 * Constructeur de la classe Buffer
  	 */
  	public Buffer(){
  		zoneTexte = new StringBuffer();
  	}

  	/**
  	 * Permet de prendre le contenu du Buffer
  	 * 
  	 * @return	Le contenu du buffer
  	 */
  	public String getZoneTexte() {
  		return zoneTexte.toString();
  	}
  	
  	/**
  	 * Permet de mettre un nouveau texte au Buffer
  	 * 
  	 * @param zone	Le nouveau texte
  	 */
  	public void setZoneTexte(String zone) {
  		zoneTexte = new StringBuffer(zone);
  	}
  	
  	/**
  	 * Permet de prendre les caract�res entre l'indice de d�but et l'indice de fin
  	 * 
  	 * @param debut		L'indice de  d�but
  	 * @param fin		L'indice de fin
  	 * 
  	 * @return	Le(s) caract�re(s) entre l'indice de d�but et l'indice de fin
  	 */
  	public String getInterval(int debut, int fin){
  		return zoneTexte.substring(debut, fin);
  	}
  	
  	/**
  	 * Permet de supprimer les caract�res entre l'indice de d�but et l'indice de fin
  	 * 
  	 * @param debut	L'indice de d�but
  	 * @param fin	L'indice de fin
  	 */
  	public void removeInterval(int debut, int fin){
  		String s = "";
  		
  		// On met le d�but (de 0 � debut)
  		s += debut 	> 0 					? zoneTexte.substring(0, debut) 					: "";
  		
  		// On met la fin (de fin � longueurMax)
  		s += fin 	< zoneTexte.length() 	? zoneTexte.substring(fin, zoneTexte.length()) 	: "";
  		
  		zoneTexte = new StringBuffer(s);
  	}
  	
  	/**
  	 * Permet d'ajouter un texte au buffer � l'indice voulu
  	 * 
  	 * @param texte		Le texte � ajouter
  	 * @param debut		L'indice o� on souhaite ajouter
  	 */
  	public void addText(String texte, int debut){
  		String s = "";
  		
  		// On met le d�but
  		s += debut >= 0 	? zoneTexte.substring(0, debut + 1) : "";
  		
  		// On ins�re le texte
  		s += texte;
  		
  		// On met la fin
  		s += debut+1 < zoneTexte.length() ? zoneTexte.substring(debut+1, zoneTexte.length()) 	: "";
  		
  		zoneTexte = new StringBuffer(s);
  	}
  	
  	/* (non-Javadoc)
  	 * @see java.lang.Object#toString()
  	 */
  	public String toString(){
  		return zoneTexte + "";
  	}
}
