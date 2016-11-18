package fr.istic.m1.aco.miniediteur.v1.receiver;

class MEImpl implements MoteurEdition {
	private Buffer buffer ;
	private PressePapier pressePapier ;
	private Selection selection ;

	public void couper(){
		// S'il y a eu une sélection
		if(selection.selectionne()){
			// On copie
			pressePapier.setPressePapier(buffer.getInterval(selection.getDebut(), selection.getFin()));
			
			// On jarte
			buffer.removeInterval(selection.getDebut(), selection.getFin());
		}
	}
	
	public void copier() {
		// S'il y a eu une sélection
		if(selection.selectionne()){
			// On copie
			pressePapier.setPressePapier(buffer.getInterval(selection.getDebut(), selection.getFin()));
		}
	}
	
	public void coller() {
		// Si du texte a été sélectionné
		if(selection.selectionne()){
			// On jarte
			buffer.removeInterval(selection.getDebut(), selection.getFin());
		}
		
		// On ajoute
		buffer.addText(pressePapier.getPressePapier(), selection.getDebut());
	}
	
	public void insererTexte() {
		// TODO
		String texte = buffer.getZoneTexte();
	}
	
	public void selectionner() {
		// TODO
		selection.setDebut(1);
		selection.setFin(1);
	}

}
