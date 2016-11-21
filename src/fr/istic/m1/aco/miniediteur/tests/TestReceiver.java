package fr.istic.m1.aco.miniediteur.tests;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestReceiver {
	private MEImpl moteurEdition;

	@Before
	public void setUp() throws Exception {
		moteurEdition = new MEImpl();
	}

	@Test
	public void casInsertion1() {
		System.out.println("=== Cas Insertion 1 ===");
		
		// Test d'ajout de "Le lapin"
		moteurEdition.insererTexte("Le lapin");
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");
		
		assertEquals("Le lapin", moteurEdition.getBuffer());
		
		// Test d'ajout de "qui s'appelle"
		moteurEdition.insererTexte(" qui s'appelle");
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");
		
		assertEquals("Le lapin qui s'appelle", moteurEdition.getBuffer());
		
		// Test d'ajout de "Oscar Hotte"
		moteurEdition.insererTexte(" Oscar Hotte");
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");
		
		assertEquals("Le lapin qui s'appelle Oscar Hotte".length(), moteurEdition.getCurseur());
		assertEquals("Le lapin qui s'appelle Oscar Hotte", moteurEdition.getBuffer());
	}
	
	@Test
	public void casSuppression1(){
		System.out.println("=== Cas Supression 1 ===");
		
		// Test de suppression dans le vide
		moteurEdition.supprimerTexte(5);
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");
		assertEquals(0, moteurEdition.getCurseur());
		
		// Test d'ajout d'une phrase
		moteurEdition.insererTexte("Une phrase ajoutée au buffer");
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Une phrase ajoutée au buffer", moteurEdition.getBuffer());
		
		// Test de suppression de " buffer"
		moteurEdition.supprimerTexte(7);
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Une phrase ajoutée au", moteurEdition.getBuffer());
		
		// Test de suppression pour ne laisser plus que "Une"
		moteurEdition.supprimerTexte(18);
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Une".length(), moteurEdition.getCurseur());
		assertEquals("Une", moteurEdition.getBuffer());
		
		// Test d'ajout de mots
		moteurEdition.insererTexte(" bouteille à la mer");
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Une bouteille à la mer".length(), moteurEdition.getCurseur());
		assertEquals("Une bouteille à la mer", moteurEdition.getBuffer());
		
		// Test de suppression avec un indice supérieur à la longueur du mot
		moteurEdition.supprimerTexte(1800);
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals(0, moteurEdition.getCurseur());
		assertEquals("", moteurEdition.getBuffer());
	}
	
	@Test
	public void casSuppression2(){
		System.out.println("=== Cas Supression 2 ===");
		
		// Initialisation de la phrase
		moteurEdition.insererTexte("Un chimpanzé mangeant une banane et un abricot");
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Un chimpanzé mangeant une banane et un abricot", moteurEdition.getBuffer());
		
		// Test de déplacement du curseur après "Un chimpanzé mangeant "
		moteurEdition.setCurseur(22);
		assertEquals(moteurEdition.getCurseur(), 22);
		
		// Test de suppression de "Un chimpanzé mangeant "
		moteurEdition.supprimerTexte(1800);
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals(0, moteurEdition.getCurseur());
		assertEquals("une banane et un abricot", moteurEdition.getBuffer());
		
		// Test d'ajout de "Achète-moi "
		moteurEdition.insererTexte("Achète-moi ");
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Achète-moi une banane et un abricot", moteurEdition.getBuffer());
	}
	
	@Test
	public void casCopierColler1(){
		System.out.println("=== Cas Copier Coller 1 ===");
		
		// Initialisation de la phrase
		moteurEdition.insererTexte("Ici le texte");
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Ici le texte", moteurEdition.getBuffer());
		
		// Copie de "le"
		moteurEdition.selectionnerDebut(4);
		moteurEdition.selectionnerFin(6);
		moteurEdition.copier();
		System.out.println("> " + moteurEdition.getPressePapier() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("le".length(), (moteurEdition.getPressePapier()).length());
		assertEquals("le", moteurEdition.getPressePapier());
		
		// On se place après "le" et on colle "le"
		moteurEdition.setCurseur(6);
		moteurEdition.coller();
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Ici lele texte".length(), (moteurEdition.getBuffer()).length());
		assertEquals("Ici lele texte", moteurEdition.getBuffer());
		
		// On copie "texte"
		moteurEdition.selectionnerDebut(9);
		moteurEdition.selectionnerFin(14);
		moteurEdition.copier();
		System.out.println("> " + moteurEdition.getPressePapier() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("texte".length(), (moteurEdition.getPressePapier()).length());
		assertEquals("texte", moteurEdition.getPressePapier());
		
		// On se place après "texte" et on colle "texte"
		moteurEdition.setCurseur(14);
		moteurEdition.coller();
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Ici lele textetexte".length(), (moteurEdition.getBuffer()).length());
		assertEquals("Ici lele textetexte", moteurEdition.getBuffer());
		
		// On essaie de tester les limites
		moteurEdition.selectionnerDebut(-5);
		moteurEdition.selectionnerFin(900);
		moteurEdition.copier();
		System.out.println("> " + moteurEdition.getPressePapier() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Ici lele textetexte".length(), (moteurEdition.getPressePapier()).length());
		assertEquals("Ici lele textetexte", moteurEdition.getPressePapier());
		
		// On colle à la fin
		moteurEdition.coller();
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Ici lele textetexteIci lele textetexte".length(), (moteurEdition.getBuffer()).length());
		assertEquals("Ici lele textetexteIci lele textetexte", moteurEdition.getBuffer());
	}
	
	@Test
	public void casCouperColler1(){
		System.out.println("=== Cas Couper Coller 1 ===");
		
		// Initialisation de la phrase
		moteurEdition.insererTexte("Ici le texte");
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Ici le texte", moteurEdition.getBuffer());
		
		// On coupe "le"
		moteurEdition.selectionnerDebut(4);
		moteurEdition.selectionnerFin(6);
		moteurEdition.couper();
		System.out.println("> " + moteurEdition.getPressePapier() + " (" + moteurEdition.getCurseur() + ")");
		System.out.println(">> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("le".length(), (moteurEdition.getPressePapier()).length());
		assertEquals("le", moteurEdition.getPressePapier());
		assertEquals("Ici  texte".length(), (moteurEdition.getBuffer()).length());
		assertEquals("Ici  texte", moteurEdition.getBuffer());
		
		// On remet le "le"
		moteurEdition.coller();
		System.out.println("> " + moteurEdition.getBuffer() + " (" + moteurEdition.getCurseur() + ")");

		assertEquals("Ici le texte".length(), moteurEdition.getBuffer().length());
		assertEquals("Ici le texte", moteurEdition.getBuffer());
	}
}
