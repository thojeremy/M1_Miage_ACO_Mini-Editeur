package fr.istic.m1.aco.miniediteur.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.m1.aco.miniediteur.v3.UndoRedo.*;

public class TestUndoRedo {
	private UndoRedoOriginator originator;
	private UndoRedoCareTaker undo;
	private UndoRedoCareTaker redo;

	/**
	 * Permet de dire quoi faire avant chaque test
	 * 
	 * @throws Exception	S'il y a une exception
	 */
	@Before
	public void setUp() throws Exception {
		originator = new UndoRedoOriginator();
		undo = new UndoRedoCareTaker();
		redo = new UndoRedoCareTaker();
	}
	
	/**
	 * Permet de tester l'initialisation
	 */
	@Test
	public void init(){
		assertEquals(originator.getEtat(), "");
		
		assertEquals(undo.size(), 0);
		assertEquals(redo.size(), 0);
	}
	
	/**
	 * Permet de supprimer la dernière lettre de Marsupilami
	 */
	@Test
	public void casMarsupilamiDerniereLettre(){
		System.out.println("=== Cas Marsupilami Derniere Lettre ===");
		
		// Ecriture de Marsupilami dans le undo
		undo.add(new UndoRedoMemento("M"));
		undo.add(new UndoRedoMemento("Ma"));
		undo.add(new UndoRedoMemento("Mar"));
		undo.add(new UndoRedoMemento("Mars"));
		undo.add(new UndoRedoMemento("Marsu"));
		undo.add(new UndoRedoMemento("Marsup"));
		undo.add(new UndoRedoMemento("Marsupi"));
		undo.add(new UndoRedoMemento("Marsupil"));
		undo.add(new UndoRedoMemento("Marsupila"));
		undo.add(new UndoRedoMemento("Marsupilam"));
		undo.add(new UndoRedoMemento("Marsupilami"));
		
		assertEquals(undo.size(), "Marsupilami".length());
		
		// On prend le i du undo
		originator.fromMemento(undo.get());
		System.out.println("undo.get -> " + originator.getEtat());
		
		assertEquals(originator.getEtat(), "Marsupilami");
		
		// Pour le mettre dans le redo
		redo.add(originator.toMemento());
		
		assertEquals(redo.size(), 1);
		
		// On prend le i du redo
		originator.fromMemento(redo.get());
		System.out.println("redo.get -> " + originator.getEtat());
		
		assertEquals(originator.getEtat(), "Marsupilami");
		
		// Pour le mettre dans le undo
		undo.add(originator.toMemento());
		
		assertEquals(undo.size(), "Marsupilami".length());
	}
	
	/**
	 * Permet de laisser seulement le premier mot
	 */
	@Test
	public void casPhrasePremierMot(){
		System.out.println("=== Cas Phrase Premier Mot ===");
		
		// Ecriture de la phrase dans le undo
		undo.add(new UndoRedoMemento("Le"));
		undo.add(new UndoRedoMemento("Le soleil"));
		undo.add(new UndoRedoMemento("Le soleil brille"));
		undo.add(new UndoRedoMemento("Le soleil brille dans"));
		undo.add(new UndoRedoMemento("Le soleil brille dans le"));
		undo.add(new UndoRedoMemento("Le soleil brille dans le ciel"));
		
		assertEquals(undo.size(), 6);
		
		// On prend tout afin qu'il ne reste plus que "Le" dans undo
		originator.fromMemento(undo.get());
		redo.add(originator.toMemento());
		System.out.println("undo -> redo : " + originator.getEtat());
		
		originator.fromMemento(undo.get());
		redo.add(originator.toMemento());
		System.out.println("undo -> redo : " + originator.getEtat());
		
		originator.fromMemento(undo.get());
		redo.add(originator.toMemento());
		System.out.println("undo -> redo : " + originator.getEtat());
		
		originator.fromMemento(undo.get());
		redo.add(originator.toMemento());
		System.out.println("undo -> redo : " + originator.getEtat());
		
		originator.fromMemento(undo.get());
		redo.add(originator.toMemento());
		System.out.println("undo -> redo : " + originator.getEtat());
		
		assertEquals(undo.size(), 1);
		assertEquals(redo.size(), 5);
		
		// On remet tout ce qu'on a mis dans redo dans undo
		originator.fromMemento(redo.get());
		undo.add(originator.toMemento());
		System.out.println("redo -> undo : " + originator.getEtat());
		
		originator.fromMemento(redo.get());
		undo.add(originator.toMemento());
		System.out.println("redo -> undo : " + originator.getEtat());
		
		originator.fromMemento(redo.get());
		undo.add(originator.toMemento());
		System.out.println("redo -> undo : " + originator.getEtat());
		
		originator.fromMemento(redo.get());
		undo.add(originator.toMemento());
		System.out.println("redo -> undo : " + originator.getEtat());
		
		originator.fromMemento(redo.get());
		undo.add(originator.toMemento());
		System.out.println("redo -> undo : " + originator.getEtat());
		
		assertEquals(undo.size(), 6);
		assertEquals(redo.size(), 0);
	}

}
