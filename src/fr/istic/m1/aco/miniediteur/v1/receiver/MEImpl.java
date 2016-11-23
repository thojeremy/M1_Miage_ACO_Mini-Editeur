package fr.istic.m1.aco.miniediteur.v1.receiver;

import fr.istic.m1.aco.miniediteur.command.Broker;
import fr.istic.m1.aco.miniediteur.command.Order;
import fr.istic.m1.aco.miniediteur.v2.macros.MacrosCareTaker;
import fr.istic.m1.aco.miniediteur.v2.macros.MacrosOriginator;
import fr.istic.m1.aco.miniediteur.v3.UndoRedo.UndoRedoCareTaker;
import fr.istic.m1.aco.miniediteur.v3.UndoRedo.UndoRedoMemento;
import fr.istic.m1.aco.miniediteur.v3.UndoRedo.UndoRedoOriginator;

public class MEImpl implements MoteurEdition {
	// V1
	private Buffer buffer ;
	private PressePapier pressePapier ;
	private Selection selection ;
	
	// V2
	MacrosOriginator m_originator;
	MacrosCareTaker macro;
	
	// V3
	private UndoRedoOriginator ur_originator;
	private UndoRedoCareTaker undo;
	private UndoRedoCareTaker redo;
	
	private int curseur;
	
	public MEImpl(){
		// V1
		buffer = new Buffer();
		pressePapier = new PressePapier();
		selection = new Selection();
		
		// V2
		m_originator = new MacrosOriginator();
		macro = new MacrosCareTaker();
		
		// V3
		ur_originator = new UndoRedoOriginator();
		undo = new UndoRedoCareTaker();
		redo = new UndoRedoCareTaker();
		
		// On initialise la liste des undo
		ajouterUndo();
		
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
		// Et on met à jour le buffer
		buffer.addText(texte, curseur-1);
		curseur += (texte.length());

		// On met à jour la liste des undos
		ajouterUndo();
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
	
	// V2
	public void ajouterMacro(Order order){
		m_originator.setEtat(order);
		macro.add(m_originator.toMemento());
	}
	
	public void jouerMacro(){
		Broker broker = new Broker();
		
		for(int i = 0; i < macro.size(); i++){
			broker.takeOrder(macro.get(i).getEtat());
		}
		
		broker.placeOrders();
	}
	
	public void supprimerMacro(){
		macro.clear();
	}

	// V3
	private void ajouterUndo(){
		ur_originator.setEtat(buffer.getZoneTexte());
		undo.add(ur_originator.toMemento());
	}
	
	public void undo(){
		if(!undo.isEmpty()){
			ur_originator.fromMemento(undo.get());
			ajouterRedo(ur_originator.toMemento());
			buffer.setZoneTexte(ur_originator.getEtat());
		}
	}
	
	private void ajouterRedo(UndoRedoMemento memento){
		redo.add(memento);
	}
	
	public void redo(){
		if(!redo.isEmpty()){
			ur_originator.fromMemento(redo.get());
			buffer.setZoneTexte(ur_originator.getEtat());
			ajouterUndo();
		}
	}
}
