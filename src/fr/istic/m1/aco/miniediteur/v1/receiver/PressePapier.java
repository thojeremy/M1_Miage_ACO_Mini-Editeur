package fr.istic.m1.aco.miniediteur.v1.receiver;

public class PressePapier {
	private String pressePapier;
	
	/**
	 * Le constructeur de la classe PressePapier
	 */
	public PressePapier(){
		pressePapier = "";
	}

  	/**
  	 * Permet de prendre le contenu du presse papier
  	 * 
  	 * @return	Le texte en cours du presse papier
  	 */
  	public String getPressePapier() {
  		return pressePapier;
  	}

	/**
	 * Permet d'attribuer un nouveau texte au persse papier
	 * 
	 * @param texte	Le texte à attribuer au presse papier
	 */
	public void setPressePapier(String texte) {
		if(texte == null){
			throw new IllegalArgumentException();
		}
		pressePapier = texte;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return pressePapier;
	}
}
