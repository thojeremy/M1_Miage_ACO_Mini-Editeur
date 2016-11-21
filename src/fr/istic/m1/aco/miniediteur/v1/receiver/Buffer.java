package fr.istic.m1.aco.miniediteur.v1.receiver;

public class Buffer {
	private StringBuffer zoneTexte;
  	
  	public Buffer(){
  		zoneTexte = new StringBuffer();
  	}

  	public String getZoneTexte() {
  		return zoneTexte.toString();
  	}
  	
  	public void setZoneTexte(String zone) {
  		zoneTexte = new StringBuffer(zone);
  	}
  	
  	public String getInterval(int debut, int fin){
  		return zoneTexte.substring(debut, fin);
  	}
  	
  	public void removeInterval(int debut, int fin){
  		String s = "";
  		
  		// On met le début (de 0 à debut)
  		s += debut 	> 0 					? zoneTexte.substring(0, debut) 					: "";
  		
  		// On met la fin (de fin à longueurMax)
  		s += fin 	< zoneTexte.length() 	? zoneTexte.substring(fin, zoneTexte.length()) 	: "";
  		
  		zoneTexte = new StringBuffer(s);
  	}
  	
  	public void addText(String texte, int debut){
  		String s = "";
  		
  		// On met le début
  		s += debut > 0 	? zoneTexte.substring(0, debut+1) : "";
  		
  		// On insère le texte
  		s += texte;
  		
  		// On met la fin
  		s += debut+1 < zoneTexte.length() ? zoneTexte.substring(debut+1, zoneTexte.length()) 	: "";
  		
  		zoneTexte = new StringBuffer(s);
  	}
  	
  	public String toString(){
  		return zoneTexte + "";
  	}
}
