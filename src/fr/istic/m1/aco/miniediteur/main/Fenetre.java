package fr.istic.m1.aco.miniediteur.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;
import fr.istic.m1.aco.miniediteur.v2.macros.MacrosCareTaker;
import fr.istic.m1.aco.miniediteur.v2.macros.MacrosOriginator;
import fr.istic.m1.aco.miniediteur.v3.UndoRedo.UndoRedoCareTaker;
import fr.istic.m1.aco.miniediteur.v3.UndoRedo.UndoRedoOriginator;
import fr.istic.m1.aco.miniediteur.command.*;

public class Fenetre extends JFrame implements MouseListener, KeyListener{
	public static final String TEXTE_REPLAY = "[MACRO] Rejouer";
	public static final String TEXTE_SAVE_1	= "[MACRO] Sauvegarder";
	public static final String TEXTE_SAVE_2	= "[MACRO] STOP";
	public static final String TEXTE_UNDO	= "Undo";
	public static final String TEXTE_REDO	= "Redo";
	public static final String TEXTE_COPIER	= "Copier";
	public static final String TEXTE_COUPER	= "Couper";
	public static final String TEXTE_COLLER	= "Coller";
	
	// Le moteur pour la V1
	private static Coller 		coller;
	private static Copier 		copier;
	private static Couper 		couper;
	private static Curseur 		curseur;
	private static Inserer 		inserer;
	private static Selectionner selectionner;
	private static Supprimer 	supprimer;
	private static Del			del;

	// Le moteur pour la V2
	private static EtatEnregistrementMacro 	etatEnregistrementMacro;
	private static EnregistrerMacro 		enregistrerMacro;
	private static JouerMacro				jouerMacro;

	// Le moteur pour la V3
	private static Undo			undo;
	private static Redo			redo;
	
	// La mise en place des mementos
	private UndoRedoOriginator urOriginator;
	private UndoRedoCareTaker urUndo;
	private UndoRedoCareTaker urRedo;
	
	private MacrosOriginator mOriginator;
	private MacrosCareTaker mUndo;
	private MacrosCareTaker mRedo;
	
	private static final int WIDTH 		= 800;
	private static final int HEIGHT 	= 800;
	
	// Les éléments visuels
	private JPanel panelGlobal, panelMenu;
	private JButton replayButton;
	private JButton saveButton;
	private JButton undoButton;
	private JButton redoButton;
	private JButton copierButton;
	private JButton couperButton;
	private JButton collerButton;
	public static JTextArea zoneTexte;
	private JScrollPane scrollPane;
	
	public Fenetre(){
		// Création de la fenêtre
		init();
		
		//Initialisation du moteur
		initialisationMoteur();
		
		// Création des éléments graphiques
		creationElementsGraphiques();
		
		// Ajout des éléments graphiques à la fenêtre
		ajoutElementsGraphiques();
		
		// Ajout des listeners
		ajoutListeners();
	}
	
	private void init(){
		setTitle("Editeur - Jérémy Tho, Emmanuel Rogard-Coat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, WIDTH, HEIGHT);
	}

	public static void initialisationMoteur(){
		// V1
		coller 			= new Coller(Main.moteur);
		copier 			= new Copier(Main.moteur);
		couper 			= new Couper(Main.moteur);
		curseur 		= new Curseur(Main.moteur);
		inserer 		= new Inserer(Main.moteur);
		selectionner 	= new Selectionner(Main.moteur);
		supprimer 		= new Supprimer(Main.moteur);
		del				= new Del(Main.moteur);
		
		// V2
		etatEnregistrementMacro	= new EtatEnregistrementMacro(Main.moteur);
		enregistrerMacro 		= new EnregistrerMacro(Main.moteur);
		jouerMacro 				= new JouerMacro(Main.moteur);
		
		// V3
		undo	= new Undo(Main.moteur);
		redo	= new Redo(Main.moteur);
	}
	
	private void creationElementsGraphiques(){
		panelGlobal = new JPanel();
		panelMenu 	= new JPanel();
		replayButton= new JButton(TEXTE_REPLAY);
		saveButton	= new JButton(TEXTE_SAVE_1);
		undoButton 	= new JButton(TEXTE_UNDO);
		redoButton 	= new JButton(TEXTE_REDO);
		copierButton= new JButton(TEXTE_COPIER);
		couperButton= new JButton(TEXTE_COUPER);
		collerButton= new JButton(TEXTE_COLLER);
		zoneTexte 	= new JTextArea();
		scrollPane 	= new JScrollPane(zoneTexte);
	}
	
	private void ajoutElementsGraphiques(){
		// Le layout global
		setLayout(new BorderLayout());
		add(panelGlobal);
		panelGlobal.setLayout(new BorderLayout());
	
		// Menu
		panelMenu	.add(copierButton);
		panelMenu	.add(couperButton);
		panelMenu	.add(collerButton);
		panelMenu	.add(replayButton);
		panelMenu	.add(saveButton);
		panelMenu	.add(undoButton);
		panelMenu	.add(redoButton);
		panelGlobal	.add(panelMenu, BorderLayout.NORTH);
		
		// Zone d'édition
		panelGlobal.add(scrollPane, BorderLayout.CENTER);
	}
	
	public void ajoutListeners(){
		// V1
		copierButton.addMouseListener(this);
		couperButton.addMouseListener(this);
		collerButton.addMouseListener(this);
		
		// V2
		replayButton.addMouseListener(this);
		saveButton	.addMouseListener(this);
		
		// V3
		undoButton	.addMouseListener(this);
		redoButton	.addMouseListener(this);
		
		// Zone d'édition
		zoneTexte	.addMouseListener(this);
		zoneTexte.addKeyListener(this);
	}
	
	private void ajouterOrderEnregistrementMacro(Order order){
		// Enregistrement dans la macro SI besoin (traité après dans le code)
		enregistrerMacro.setOrder(order);
		ExecuteCommand.addOrder(enregistrerMacro);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
			case KeyEvent.VK_BACK_SPACE:
				supprimer.setNbCaracteres(1);
				
				ExecuteCommand.addOrder(supprimer);
				
				ajouterOrderEnregistrementMacro(supprimer);
			break;
			
			case KeyEvent.VK_DELETE:
				curseur.setPosition(zoneTexte.getCaretPosition());
				del.setNbCaracteres(1);
				
				ExecuteCommand.addOrder(del);
				
				ajouterOrderEnregistrementMacro(del);
			break;
			
			case KeyEvent.VK_LEFT:
				curseur.setPosition(zoneTexte.getCaretPosition());
				
				ExecuteCommand.addOrder(curseur);
				
				ajouterOrderEnregistrementMacro(curseur);
			break;
			
			case KeyEvent.VK_RIGHT:
				curseur.setPosition(zoneTexte.getCaretPosition());
				
				ExecuteCommand.addOrder(curseur);
				
				ajouterOrderEnregistrementMacro(curseur);
			break;
			
			case KeyEvent.VK_UP:
				curseur.setPosition(zoneTexte.getCaretPosition());
				
				ExecuteCommand.addOrder(curseur);
				
				ajouterOrderEnregistrementMacro(curseur);
			break;
			
			case KeyEvent.VK_DOWN:
				curseur.setPosition(zoneTexte.getCaretPosition());
				
				ExecuteCommand.addOrder(curseur);
				
				ajouterOrderEnregistrementMacro(curseur);
			break;
			
			default:
				// Filtre pour seulement les lettres et chiffres
				int code = arg0.getKeyCode();
				
				if(		(code == 13 || code == 44 || code == 32 || code == 0 || code == 151 || code == 10) ||
						(code >= 513 && code <= 522) ||
						(code >= 48 && code <= 90) || (code >= 96 && code <= 111) || 
						(code >= 186 && code <= 222)){
					String texte = arg0.getKeyChar() + "";
					
					inserer.setTexte(texte);
					
					ExecuteCommand.addOrder(inserer);
					
					Inserer i = new Inserer(Main.moteur);
					i.setTexte(texte);
					ajouterOrderEnregistrementMacro(i);
				} else {
					System.out.println(code);
				}
			break;
		}
		
		ExecuteCommand.executeOrder();
		zoneTexte.revalidate();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
			case KeyEvent.VK_LEFT:
				curseur.setPosition(zoneTexte.getCaretPosition());
				
				ExecuteCommand.addOrder(curseur);
				
				ajouterOrderEnregistrementMacro(curseur);
			break;
			
			case KeyEvent.VK_RIGHT:
				curseur.setPosition(zoneTexte.getCaretPosition());
				
				ExecuteCommand.addOrder(curseur);
				
				ajouterOrderEnregistrementMacro(curseur);
			break;
			
			case KeyEvent.VK_UP:
				curseur.setPosition(zoneTexte.getCaretPosition());
				
				ExecuteCommand.addOrder(curseur);
				
				ajouterOrderEnregistrementMacro(curseur);
			break;
			
			case KeyEvent.VK_DOWN:
				curseur.setPosition(zoneTexte.getCaretPosition());
				
				ExecuteCommand.addOrder(curseur);
				
				ajouterOrderEnregistrementMacro(curseur);
			break;
			
			default:
			break;
		}
		
		ExecuteCommand.executeOrder();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@SuppressWarnings("static-access")
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == saveButton){
			// Un clic sur le bouton [[MACRO] Sauvegarder]
			ExecuteCommand.addOrder(etatEnregistrementMacro);
			ExecuteCommand.executeOrder();
			
			// Changement du libelle
			saveButton.setText(saveButton.getText() == TEXTE_SAVE_1 ? TEXTE_SAVE_2 : TEXTE_SAVE_1);
		} else if(e.getSource() == replayButton){
			// Un clic sur le bouton [[MACRO] Rejouer]
			if(saveButton.getText() == TEXTE_SAVE_1){// Si l'utilisateur a arrêté l'enregistrement
				ExecuteCommand.addOrder(jouerMacro);
				ExecuteCommand.executeOrder();
			} else {// Sinon
				new JOptionPane().showMessageDialog(null, "Veuillez arrêter l'enregistrement de la macro en cours", "/!\\", JOptionPane.WARNING_MESSAGE);
			}
		} else if(e.getSource() == undoButton){
			// Un clic sur le bouton [UNDO]
			ExecuteCommand.addOrder(undo);
			
			ajouterOrderEnregistrementMacro(undo);
			
			ExecuteCommand.executeOrder();
		} else if(e.getSource() == redoButton){
			// Un clic sur le bouton [REDO]
			ExecuteCommand.addOrder(redo);
			
			ajouterOrderEnregistrementMacro(redo);
			
			ExecuteCommand.executeOrder();
		} else if(e.getSource() == copierButton){
			// Un clic sur le bouton [COPIER]
			ExecuteCommand.addOrder(copier);
			
			ajouterOrderEnregistrementMacro(copier);
			
			ExecuteCommand.executeOrder();
		} else if(e.getSource() == couperButton){
			// Un clic sur le bouton [COUPER]
			ExecuteCommand.addOrder(couper);
			
			ajouterOrderEnregistrementMacro(couper);
			
			ExecuteCommand.executeOrder();
		} else if(e.getSource() == collerButton){
			// Un clic sur le bouton [COLLER]
			ExecuteCommand.addOrder(coller);
			
			ajouterOrderEnregistrementMacro(coller);
			
			ExecuteCommand.executeOrder();
		} else if(e.getSource() == zoneTexte){
			// Un clic dans la zone de texte
			curseur.setPosition(zoneTexte.getCaretPosition());
			
			ajouterOrderEnregistrementMacro(curseur);
			
			ExecuteCommand.addOrder(curseur);
			ExecuteCommand.executeOrder();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == zoneTexte){
			selectionner.setSelectionDebut(zoneTexte.getCaretPosition());
			
			ajouterOrderEnregistrementMacro(selectionner);
			
			ExecuteCommand.addOrder(selectionner);
			ExecuteCommand.executeOrder();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == zoneTexte){
			selectionner.setSelectionFin(zoneTexte.getCaretPosition());
			
			ajouterOrderEnregistrementMacro(selectionner);
			
			ExecuteCommand.addOrder(selectionner);
			ExecuteCommand.executeOrder();
		}
	}
}
