package fr.istic.m1.aco.miniediteur.v1.receiver;

class Buffer {
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
  		s += debut 	> 0 					? zoneTexte.substring(0, debut-1) 					: "";
  		
  		// On met la fin (de fin à longueurMax)
  		s += fin 	< zoneTexte.length() 	? zoneTexte.substring(fin+1, zoneTexte.length()) 	: "";
  		
  		zoneTexte = new StringBuffer(s);
  	}
  	
  	public void addText(String texte, int debut){
  		String s = "";
  		
  		// On met le début
  		s += debut > 0 	? zoneTexte.substring(0, debut-1) : "";
  		
  		// On insére le texte
  		s += texte;
  		
  		// On met la fin
  		s += debut < zoneTexte.length() ? zoneTexte.substring(debut, zoneTexte.length()) 	: "";
  		
  		zoneTexte = new StringBuffer(s);
  	}
}
