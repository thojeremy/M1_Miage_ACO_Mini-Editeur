package fr.istic.m1.aco.miniediteur.v3.tests;

import fr.istic.m1.aco.miniediteur.v3.memento.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMemento {
	private Originator originator;
	private CareTaker undo;
	private CareTaker redo;

	@Before
	public void setUp() throws Exception {
		originator = new Originator();
		undo = new CareTaker();
		redo = new CareTaker();
	}
	
	@Test
	public void init(){
		assertEquals(originator.getEtat(), "");
		
		assertEquals(undo.size(), 0);
		assertEquals(redo.size(), 0);
	}
	
	@Test
	public void casMarsupilamiDerniereLettre(){
		System.out.println("=== Cas Marsupilami Derniere Lettre ===");
		
		// Ecriture de Marsupilami dans le undo
		undo.add(new Memento("M"));
		undo.add(new Memento("Ma"));
		undo.add(new Memento("Mar"));
		undo.add(new Memento("Mars"));
		undo.add(new Memento("Marsu"));
		undo.add(new Memento("Marsup"));
		undo.add(new Memento("Marsupi"));
		undo.add(new Memento("Marsupil"));
		undo.add(new Memento("Marsupila"));
		undo.add(new Memento("Marsupilam"));
		undo.add(new Memento("Marsupilami"));
		
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
		
		// On remet tout à zéro
		originator.setEtat("");
		undo.clear();
		redo.clear();
	}
	
	@Test
	public void casPhrasePremierMot(){
		System.out.println("=== Cas Phrase Premier Mot ===");
		
		// Ecriture de la phrase dans le undo
		undo.add(new Memento("Le"));
		undo.add(new Memento("Le soleil"));
		undo.add(new Memento("Le soleil brille"));
		undo.add(new Memento("Le soleil brille dans"));
		undo.add(new Memento("Le soleil brille dans le"));
		undo.add(new Memento("Le soleil brille dans le ciel"));
		
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
