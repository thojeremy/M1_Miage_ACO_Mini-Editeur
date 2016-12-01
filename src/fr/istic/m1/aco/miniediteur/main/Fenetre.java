package fr.istic.m1.aco.miniediteur.main;

import java.awt.BorderLayout;
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
	private static EffacerRedo	effacerRedo;
	
	private static final int WIDTH 		= 800;
	private static final int HEIGHT 	= 600;
	
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
	
	/**
	 * Constructeur de la fenêtre
	 */
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
	
	/**
	 * Permet d'initialiser la fenêtre
	 */
	private void init(){
		setTitle("Editeur - Jérémy Tho, Emmanuel Rogard-Coat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, WIDTH, HEIGHT);
	}

	/**
	 * Permet d'initialiser le moteur d'édition
	 */
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
		undo		= new Undo(Main.moteur);
		redo		= new Redo(Main.moteur);
		effacerRedo = new EffacerRedo(Main.moteur);
	}
	
	/**
	 * Permet de créer les éléments graphiques de la fenêtre
	 */
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
		zoneTexte.setEditable(false);
		zoneTexte.getCaret().setVisible(true);
		scrollPane 	= new JScrollPane(zoneTexte);
	}
	
	/**
	 * Permet d'ajouter des éléments graphiques dans la fenêtre
	 */
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
	
	/**
	 * Permet d'ajouter les listeners aux éléments de la fenêtre graphique.
	 * 
	 * Cela leur permettra d'exécuter les ordres en fonction des instructions reçues de l'utilisateur.
	 */
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
	
	/**
	 * Pemret d'ajouter un ordre aux macros
	 * 
	 * @param order		L'ordre à ajouter aux macros
	 */
	private void ajouterOrderEnregistrementMacro(Order order){
		// Enregistrement dans la macro SI besoin (traité après dans le code)
		enregistrerMacro.setOrder(order);
		ExecuteCommand.addOrder(enregistrerMacro);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
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
				
				ajouterOrderEnregistrementMacro(curseur.clone());
			break;
			
			case KeyEvent.VK_RIGHT:
				curseur.setPosition(zoneTexte.getCaretPosition());
				
				ExecuteCommand.addOrder(curseur);
				
				ajouterOrderEnregistrementMacro(curseur.clone());
			break;
			
			case KeyEvent.VK_UP:
				curseur.setPosition(zoneTexte.getCaretPosition());
				
				ExecuteCommand.addOrder(curseur);
				
				ajouterOrderEnregistrementMacro(curseur.clone());
			break;
			
			case KeyEvent.VK_DOWN:
				curseur.setPosition(zoneTexte.getCaretPosition());
				
				ExecuteCommand.addOrder(curseur);
				
				ajouterOrderEnregistrementMacro(curseur.clone());
			break;
			
			default:
				// Filtre pour seulement les lettres et chiffres
				int code = arg0.getKeyCode();
				
				if(		(code == 13 || code == 44 || code == 32 || code == 0 || code == 151 || code == 10) ||
						(code >= 513 && code <= 522) ||
						(code >= 48 && code <= 90) || (code >= 96 && code <= 111) || 
						(code >= 186 && code <= 222) || (code == 153)){
					String texte = arg0.getKeyChar() + "";
					
					inserer.setTexte(texte);
					
					ExecuteCommand.addOrder(inserer);
					ExecuteCommand.addOrder(effacerRedo);
					
					ajouterOrderEnregistrementMacro(inserer.clone());
				} else {
					System.out.println(code);
				}
			break;
		}
		
		ExecuteCommand.executeOrder();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() >= 37 && arg0.getKeyCode() <= 40){
			switch(arg0.getKeyCode()){
				case KeyEvent.VK_LEFT:
					curseur.setPosition(zoneTexte.getCaretPosition());
					
					ExecuteCommand.addOrder(curseur);
					
					ajouterOrderEnregistrementMacro(curseur.clone());
				break;
				
				case KeyEvent.VK_RIGHT:
					curseur.setPosition(zoneTexte.getCaretPosition());
					
					ExecuteCommand.addOrder(curseur);
					
					ajouterOrderEnregistrementMacro(curseur.clone());
				break;
				
				case KeyEvent.VK_UP:
					curseur.setPosition(zoneTexte.getCaretPosition());
					
					ExecuteCommand.addOrder(curseur);
					
					ajouterOrderEnregistrementMacro(curseur.clone());
				break;
				
				case KeyEvent.VK_DOWN:
					curseur.setPosition(zoneTexte.getCaretPosition());
					
					ExecuteCommand.addOrder(curseur);
					
					ajouterOrderEnregistrementMacro(curseur.clone());
				break;
				
				default:
				break;
			}
		
			ExecuteCommand.executeOrder();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
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
			
			ajouterOrderEnregistrementMacro(curseur.clone());
			
			ExecuteCommand.addOrder(curseur);
			ExecuteCommand.executeOrder();
		}
		
		zoneTexte.getCaret().setVisible(true);
		zoneTexte.requestFocus();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == zoneTexte){
			// On change la position du curseur
			curseur.setPosition(zoneTexte.getCaretPosition());
			ajouterOrderEnregistrementMacro(curseur.clone());
			
			ExecuteCommand.addOrder(curseur);
			
			// On change le début de la sélection
			selectionner.setSelectionDebut(zoneTexte.getCaretPosition());
			ajouterOrderEnregistrementMacro(selectionner.clone());
			
			ExecuteCommand.addOrder(selectionner);
			ExecuteCommand.executeOrder();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == zoneTexte){
			// On change la position du curseur
			curseur.setPosition(zoneTexte.getCaretPosition());
			ajouterOrderEnregistrementMacro(curseur.clone());
			
			ExecuteCommand.addOrder(curseur);
			
			// On change la position du curseur
			selectionner.setSelectionFin(zoneTexte.getCaretPosition());
			ajouterOrderEnregistrementMacro(selectionner.clone());
			
			ExecuteCommand.addOrder(selectionner);
			ExecuteCommand.executeOrder();
		}
	}
}
