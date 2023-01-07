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
	RegisterPanel registerPanel;
	JButton comicSearchButton;
	JButton characterSearchButton;
	JButton loginButton;
	JButton registerButton;
	JButton signOutButton;

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
		panel.setLayout(null);
		window.getContentPane().add(panel);

		// Création des boutons de navigation
		comicSearchButton = new JButton("Comic search"); // Bouton pour accéder à la recherche pour les comics
		comicSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		comicSearchButton.setBounds(10, 6, 130, 23);
		comicSearchButton.setVisible(false);
		
		characterSearchButton = new JButton("Character search"); // Bouton pour accéder à la recherche pour les
		characterSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		characterSearchButton.setBounds(10, 56, 130, 23);
		characterSearchButton.setVisible(false);
		
		loginButton = new JButton("Login"); // Bouton pour accéder à la partie connexion au compte
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		loginButton.setBounds(10, 6, 130, 23);
		
		registerButton = new JButton("Register");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		registerButton.setBounds(10, 56, 130, 23);
		
		signOutButton = new JButton("Sign out");
		signOutButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		signOutButton.setBounds(10, 480, 130, 23);
		signOutButton.setVisible(false);
		
		// Ajout des boutons de navigation au panel principal
		panel.add(comicSearchButton); // ajout du bouton au panel
		panel.add(characterSearchButton); // ajout du bouton au panel
		panel.add(loginButton); // ajout du bouton au panel
		panel.add(registerButton);
		panel.add(signOutButton);
		
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
		registerButton.addMouseListener(mouselistener);

		// Création des panels de recherche
		comicPanel = new ComicSearchPanel();
		comicPanel.setVisible(false);
		charPanel = new CharacterSearchPanel();
		charPanel.setVisible(false);
		loginPanel = new LoginPanel(this);
		loginPanel.setVisible(false);
		registerPanel = new RegisterPanel(this);
		registerPanel.setVisible(false);
		// Ajout des panels de recherche au panel principal
		window.getContentPane().add(comicPanel);
		window.getContentPane().add(charPanel);
		window.getContentPane().add(loginPanel);
		window.getContentPane().add(registerPanel);

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
			registerPanel.setVisible(false);
			break;
		case 1:
			comicPanel.setVisible(false);
			loginPanel.setVisible(false);
			registerPanel.setVisible(false);
			charPanel.setVisible(true);
			break;
		case 2:
			comicPanel.setVisible(false);
			charPanel.setVisible(false);
			loginPanel.setVisible(true);
			registerPanel.setVisible(false);
			break;
		case 3:
			comicPanel.setVisible(false);
			charPanel.setVisible(false);
			loginPanel.setVisible(false);
			registerPanel.setVisible(true);
			break;
		default:
			comicPanel.setVisible(false);
			charPanel.setVisible(false);
			loginPanel.setVisible(false);
			registerPanel.setVisible(false);
		}

	}
	public void setUser(String login, boolean premium) {
		user = new User(login, premium);
		userLabel.setText(login);
		registerButton.setVisible(false);
		loginButton.setVisible(false);
		comicSearchButton.setVisible(true);
		characterSearchButton.setVisible(true);
		signOutButton.setVisible(true);
		
	}
	public void signOut() {
		user = null;
		userLabel.setText("");
		registerButton.setVisible(true);
		loginButton.setVisible(true);
		comicSearchButton.setVisible(false);
		characterSearchButton.setVisible(false);
		setDisplayedPanel(-1);
	}
}