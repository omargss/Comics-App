package DisplayScreen;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import Listeners.*;
import Objects.User;
import java.awt.Font;

public class MainWindow {
	JFrame window = null;
	int width = 1200;
	int height = 600;
	ComicSearchPanel comicPanel;
	CharacterSearchPanel charPanel;
	LoginPanel loginPanel;
	User user;
	JLabel userLabel;

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

		// Création des boutons de navigation
		JButton comicSearchButton = new JButton("Comic search"); // Bouton pour accéder à la recherche pour les comics
		comicSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		comicSearchButton.setBounds(10, 6, 130, 23);
		JButton characterSearchButton = new JButton("Character search"); // Bouton pour accéder à la recherche pour les
		characterSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		characterSearchButton.setBounds(10, 56, 130, 23);
																			// personnages
		JButton loginButton = new JButton("Login"); // Bouton pour accéder à la partie connexion au compte
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		loginButton.setBounds(10, 110, 130, 23);
		panel.setLayout(null);

		// Ajout des boutons de navigation au panel principal
		panel.add(comicSearchButton); // ajout du bouton au panel
		panel.add(characterSearchButton); // ajout du bouton au panel
		panel.add(loginButton); // ajout du bouton au panel
		
		userLabel = new JLabel("");
		userLabel.setEnabled(false);
		userLabel.setBounds(10, 525, 130, 15);
		panel.add(userLabel);

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
		loginPanel = new LoginPanel(this);
		loginPanel.setVisible(false);
		// Ajout des panels de recherche au panel principal
		window.getContentPane().add(comicPanel);
		window.getContentPane().add(charPanel);
		window.getContentPane().add(loginPanel);
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
			loginPanel.setVisible(false);
			charPanel.setVisible(false);
			break;
		case 1:
			comicPanel.setVisible(false);
			loginPanel.setVisible(false);
			charPanel.setVisible(true);
			break;
		case 2:
			comicPanel.setVisible(false);
			charPanel.setVisible(false);
			loginPanel.setVisible(true);
			break;
		default:
			comicPanel.setVisible(false);
			charPanel.setVisible(false);
			loginPanel.setVisible(false);
		}

	}
	public void setUser(String login, boolean premium) {
		user = new User(login, premium);
		userLabel.setText(login);
	}
}