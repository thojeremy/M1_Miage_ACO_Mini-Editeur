package fr.istic.m1.aco.miniediteur.v1.receiver;

import fr.istic.m1.aco.miniediteur.command.Broker;
import fr.istic.m1.aco.miniediteur.command.Order;
import fr.istic.m1.aco.miniediteur.v2.macros.MacrosCareTaker;
import fr.istic.m1.aco.miniediteur.v2.macros.MacrosOriginator;
import fr.istic.m1.aco.miniediteur.v3.UndoRedo.UndoRedoCareTaker;
import fr.istic.m1.aco.miniediteur.v3.UndoRedo.UndoRedoMemento;
import fr.istic.m1.aco.miniediteur.v3.UndoRedo.UndoRedoOriginator;

/**
 * @author jérémy
 *
 */
public class MEImpl implements MoteurEdition {
	// V1
	private Buffer buffer ;
	private PressePapier pressePapier ;
	private Selection selection ;
	
	// V2
	boolean enregistrementMacro;
	boolean jouerMacro;
	MacrosOriginator m_originator;
	MacrosCareTaker macro;
	
	// V3
	private UndoRedoOriginator ur_originator;
	private UndoRedoCareTaker undo;
	private UndoRedoCareTaker redo;
	
	public static int curseur;
	public static int curseurDebutJouerMacro;
	
	/**
	 * Le constructeur du moteur d'édition
	 */
	public MEImpl(){
		// V1
		buffer = new Buffer();
		pressePapier = new PressePapier();
		selection = new Selection();
		
		// V2
		enregistrementMacro = false;
		jouerMacro = false;
		m_originator = new MacrosOriginator();
		macro = new MacrosCareTaker();
		
		// V3
		ur_originator = new UndoRedoOriginator();
		undo = new UndoRedoCareTaker();
		redo = new UndoRedoCareTaker();
		
		// On initialise la liste des undo
		ajouterUndo();

		curseur = curseurDebutJouerMacro = 0;
	}

	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#couper()
	 */
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
			
			// On stocke dans le undo
			ur_originator.setEtat(buffer.getZoneTexte());
			undo.add(ur_originator.toMemento());
			
			// On change la position du curseur
			curseur = deb;
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#copier()
	 */
	public void copier() {
		// S'il y a eu une sélection
		if(selection.selectionne()){
			// On teste le début et la fin
			selection.gestionDebutFin();
			
			// On copie
			int deb = selection.getDebut() < 0 ? 0 : selection.getDebut();
			int fin = selection.getFin() >= buffer.getZoneTexte().length() ? buffer.getZoneTexte().length() : selection.getFin();
			
			pressePapier.setPressePapier(buffer.getInterval(deb, fin));
			
			// On change la position du curseur
			curseur = deb;
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#coller()
	 */
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
			
			// On stocke dans le undo
			ur_originator.setEtat(buffer.getZoneTexte());
			undo.add(ur_originator.toMemento());
			
			curseur += pressePapier.getPressePapier().length();
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#insererTexte(java.lang.String)
	 */
	public void insererTexte(String texte) {
		// Et on met à jour le buffer
		buffer.addText(texte, curseur-1);
		curseur += (texte.length());

		// On met à jour la liste des undos
		ajouterUndo();
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#selectionnerDebut(int)
	 */
	public void selectionnerDebut(int debut) {
		selection.setDebut(debut < 0 ? 0 : (!jouerMacro ? debut : (curseurDebutJouerMacro + debut)));
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#selectionnerFin(int)
	 */
	public void selectionnerFin(int fin){
		selection.setFin(fin >= buffer.getZoneTexte().length() ? buffer.getZoneTexte().length() : (!jouerMacro ? fin : (curseurDebutJouerMacro + fin)));
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#supprimerTexte(int)
	 */
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
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#supprimerTexteDel(int)
	 */
	public void supprimerTexteDel(int nombre){
		if((curseur + nombre) <= buffer.getZoneTexte().length()){
			curseur += nombre;
			supprimerTexte(nombre);
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#getBuffer()
	 */
	public String getBuffer(){
		return buffer+"";
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#getPressePapier()
	 */
	public String getPressePapier(){
		return pressePapier.getPressePapier();
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#setCurseur(int)
	 */
	public void setCurseur(int position){
		curseur = position < 0 ? 0 : (position > buffer.getZoneTexte().length() ? buffer.getZoneTexte().length() : (!jouerMacro ? position : (curseurDebutJouerMacro + position)));
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#getCurseur()
	 */
	public int getCurseur(){
		return curseur;
	}
	
	// V2
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#ajouterMacro(fr.istic.m1.aco.miniediteur.command.Order)
	 */
	public void ajouterMacro(Order order){
		if(enregistrementMacro){
			m_originator.setEtat(order);
			macro.add(m_originator.toMemento());
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#changerEtatEnregistrementMacro()
	 */
	public void changerEtatEnregistrementMacro(){
		enregistrementMacro = !enregistrementMacro;
		
		if(enregistrementMacro){
			macro.clear();
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#changerEtatJouerMacro()
	 */
	public void changerEtatJouerMacro(){
		enregistrementMacro = false;
		jouerMacro = !jouerMacro;
		curseurDebutJouerMacro = curseur;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#jouerMacro()
	 */
	public void jouerMacro(){
		Broker broker = new Broker();
		
		for(int i = 0; i < macro.size(); i++){
			broker.takeOrder(macro.get(i).getEtat());
		}

		changerEtatJouerMacro();

		broker.placeOrders();

		changerEtatJouerMacro();
	}

	// V3
	/**
	 * Permet d'ajouter l'état courant du buffer dans la liste undo
	 */
	private void ajouterUndo(){
		ur_originator.setEtat(buffer.getZoneTexte());
		undo.add(ur_originator.toMemento());
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#undo()
	 */
	public void undo(){
		if(!undo.isEmpty()){
			ur_originator.fromMemento(undo.get());
			ajouterRedo(ur_originator.toMemento());
			buffer.setZoneTexte(ur_originator.getEtat());
			curseur = buffer.getZoneTexte().length();
		}
	}
	
	/**
	 * Permet d'ajouter un Memento au redo
	 * 
	 * @param memento	Le memento à ajouter
	 */
	private void ajouterRedo(UndoRedoMemento memento){
		redo.add(memento);
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#redo()
	 */
	public void redo(){
		if(!redo.isEmpty()){
			ur_originator.fromMemento(redo.get());
			buffer.setZoneTexte(ur_originator.getEtat());
			curseur = buffer.getZoneTexte().length();
			ajouterUndo();
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition#effacerRedo()
	 */
	public void effacerRedo(){
		redo.clear();
	}
}
