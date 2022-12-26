package DisplayScreen;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import Listeners.*;

public class MainWindow {
	JFrame window = null;
	int width = 1200;
	int height = 600;
	ComicSearchPanel comicPanel;
	CharacterSearchPanel charPanel;

	/**
	 * Constructeur de la classe
	 */
	public MainWindow() {
		// Définition de la fenetre d'affichage
		window = new JFrame(); // permet de définir la var comme nouvelle Frame
		window.setVisible(true); // permet de savoir si elle est visible ou non
		window.setPreferredSize(new Dimension(width, height)); // dimension de la fenetre
		window.setSize(new Dimension(width, height)); // dimension de la fenetre
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer le processus quand on quitte la
																// fenetre java
		window.pack();
		window.getContentPane().setLayout(null);

		// Création du panel latéral pour naviguer entre les différents onglets
		JPanel panel = new JPanel(); // panel latéral pour acceuillir les boutons spécifiant la recherche
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 150, 600);
		panel.setVisible(true);
		window.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Création des boutons de navigation
		JButton comicSearchButton = new JButton("Comic search"); // Bouton pour accéder à la recherche pour les comics
		JButton characterSearchButton = new JButton("Character search"); // Bouton pour accéder à la recherche pour les
																			// personnages
		JButton loginButton = new JButton("Login"); // Bouton pour accéder à la partie connexion au compte

		// Ajout des boutons de navigation au panel principal
		panel.add(comicSearchButton); // ajout du bouton au panel
		panel.add(characterSearchButton); // ajout du bouton au panel
		panel.add(loginButton); // ajout du bouton au panel

		// LISTENERS:
		MainWindowListener mouselistener = new MainWindowListener(this);
		//
		comicSearchButton.addMouseListener(mouselistener);
		characterSearchButton.addMouseListener(mouselistener);
		loginButton.addMouseListener(mouselistener);

		// Création des panels de recherche
		comicPanel = new ComicSearchPanel();
		comicPanel.setVisible(false);
		charPanel = new CharacterSearchPanel();
		charPanel.setVisible(false);

		// Ajout des panels de recherche au panel principal
		window.getContentPane().add(comicPanel);
		window.getContentPane().add(charPanel);
	}

	/**
	 * Permet d'afficher ou de cacher un panel
	 * 
	 * @param i : int permettant de définir l'affichage ou non d'un panel
	 */
	public void setDisplayedPanel(int i) {
		switch (i) {
		case 0:
			comicPanel.setVisible(true);
			charPanel.setVisible(false);
			break;
		case 1:
			comicPanel.setVisible(false);
			charPanel.setVisible(true);
		default:
			break;
		}

	}
}