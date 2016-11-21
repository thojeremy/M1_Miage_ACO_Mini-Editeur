package fr.istic.m1.aco.miniediteur.v1.receiver;

public class PressePapier {
	private String pressePapier;
	
	public PressePapier(){
		pressePapier = "";
	}

  	public String getPressePapier() {
  		return pressePapier;
  	}

	public void setPressePapier(String texte) {
		if(texte == null){
			throw new IllegalArgumentException();
		}
		pressePapier = texte;
	}
	
	public String toString(){
		return pressePapier;
	}
}
