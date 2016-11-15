package fr.istic.m1.aco.miniediteur.main;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.istic.m1.aco.miniediteur.v1.*;

public class Fenetre extends JFrame{
	private static final int WIDTH 		= 500;
	private static final int HEIGHT 	= 500;
	
	private JPanel panelGlobal, panelMenu;
	private JButton newButton;
	private JButton saveButton;
	private JButton undoButton;
	private JButton redoButton;
	private JTextArea zoneTexte;
	
	public Fenetre(){
		// Cr�ation de la fen�tre
		init();
		
		// Cr�ation des �l�ments graphiques
		creationElementsGraphiques();
		
		// Ajout des �l�ments graphiques � la fen�tre
		ajoutElementsGraphiques();
	}
	
	private void init(){
		setTitle("Editeur - J�r�my Tho, Emmanuel Rogard-Coat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, WIDTH, HEIGHT);
	}
	
	private void creationElementsGraphiques(){
		panelGlobal = new JPanel();
		panelMenu 	= new JPanel();
		newButton 	= new JButton("Nouveau");
		saveButton	= new JButton("Sauvegarder");
		undoButton 	= new JButton("Undo");
		redoButton 	= new JButton("Redo");
		zoneTexte 	= new JTextArea();
	}
	
	private void ajoutElementsGraphiques(){
		// Le layout global
		setLayout(new BorderLayout());
		add(panelGlobal);
		panelGlobal.setLayout(new BorderLayout());
	
		// Menu
		panelMenu.add(newButton);
		panelMenu.add(saveButton);
		panelMenu.add(undoButton);
		panelMenu.add(redoButton);
		panelGlobal.add(panelMenu, BorderLayout.NORTH);
		
		// Zone d'�dition
		panelGlobal.add(zoneTexte, BorderLayout.CENTER);
	}
}