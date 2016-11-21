package fr.istic.m1.aco.miniediteur.v1.receiver;

public class MEImpl implements MoteurEdition {
	private Buffer buffer ;
	private PressePapier pressePapier ;
	private Selection selection ;
	private int curseur;
	
	public MEImpl(){
		buffer = new Buffer();
		pressePapier = new PressePapier();
		selection = new Selection();
		curseur = 0;
	}

	public void couper(){
		// S'il y a eu une sélection
		if(selection.selectionne()){
			selection.gestionDebutFin();
			
			int deb = selection.getDebut() < 0 ? 0 : selection.getDebut();
			int fin = selection.getFin() >= buffer.getZoneTexte().length() ? buffer.getZoneTexte().length() : selection.getFin();
			
			// On copie
			pressePapier.setPressePapier(buffer.getInterval(deb, fin));
			
			// On enlève l'intervalle voulu
			buffer.removeInterval(deb, fin);
			
			// On change la position du curseur
			curseur = deb;
		}
	}
	
	public void copier() {
		// S'il y a eu une sélection
		if(selection.selectionne()){
			// On teste le début et la fin
			selection.gestionDebutFin();
			
			// On copie
			int deb = selection.getDebut() < 0 ? 0 : selection.getDebut();
			int fin = selection.getFin() >= buffer.getZoneTexte().length() ? buffer.getZoneTexte().length() : selection.getFin();
			
			pressePapier.setPressePapier(buffer.getInterval(deb, fin));
		}
	}
	
	public void coller() {
		// Si du texte a été sélectionné
		if(pressePapier.toString().length() > 0){
			// On prend le texte du buffer
			String texte = buffer.getZoneTexte();
			
			// On prend la partie du texte du début jusqu'au curseur
			// On ajoute le texte du presse papier
			// On ajoute la partie du texùte du curseur à la fin
			texte = texte.substring(0, curseur) + pressePapier.getPressePapier() + texte.substring(curseur, texte.length());
			
			// On met le nouveau texte dans le buffer
			buffer.setZoneTexte(texte);
			
			curseur += pressePapier.getPressePapier().length();
		}
	}
	
	public void insererTexte(String texte) {
		buffer.addText(texte, curseur-1);
		curseur += (texte.length());
	}
	
	public void selectionnerDebut(int debut) {
		selection.setDebut(debut);
	}
	
	public void selectionnerFin(int fin){
		selection.setFin(fin);
	}
	
	public void supprimerTexte(int nombre){
		String texte = buffer.getZoneTexte();
		
		if(texte.substring(0, curseur).length() - nombre >= 0){
			texte = texte.substring(0, (curseur)-nombre) + texte.substring(curseur, texte.length());
		} else {
			texte = "" + texte.substring(curseur, texte.length());
		}
		
		buffer.setZoneTexte(texte);
		curseur = (curseur - nombre >= 0) ? (curseur-nombre) : 0 ;
	}
	
	public void supprimerTexteDel(int nombre){
		if((curseur + nombre) <= buffer.getZoneTexte().length()){
			curseur += nombre;
			supprimerTexte(nombre);
		}
	}
	
	public String getBuffer(){
		return buffer+"";
	}
	
	public String getPressePapier(){
		return pressePapier.getPressePapier();
	}
	
	public void setCurseur(int position){
		curseur = position < 0 ? 0 : (position > buffer.getZoneTexte().length() ? buffer.getZoneTexte().length() : position);
	}
	
	public int getCurseur(){
		return curseur;
	}
}
