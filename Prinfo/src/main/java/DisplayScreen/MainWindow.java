package DisplayScreen;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import Listeners.*;
import Objects.User;
import java.awt.Font;
import java.awt.BorderLayout;

public class MainWindow {
	JFrame window = null;
	int width = 1200;
	int height = 600;
	ComicSearchPanel comicPanel;
	CharacterSearchPanel charPanel;
	PublisherSearchPanel publPanel;
	LikedMenuPanel likedPanel;
	StateMenuPanel StatePanel;
	LoginPanel loginPanel;
	User user;
	JLabel userLabel;
	RegisterPanel registerPanel;
	JButton comicSearchButton;
	JButton characterSearchButton;
	JButton publisherSearchButton;
	JButton loginButton;
	JButton registerButton;
	JButton signOutButton;
	JButton LikedMenuButton;
	JButton StateMenuButton;

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

		publisherSearchButton = new JButton("Publisher search"); // Bouton pour accéder à la recherche pour les
		publisherSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		publisherSearchButton.setBounds(10, 106, 130, 23);
		publisherSearchButton.setVisible(false);

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

		LikedMenuButton = new JButton("Liked"); // Bouton pour accéder à la page des
		LikedMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		LikedMenuButton.setBounds(10, 156, 130, 23);
		LikedMenuButton.setVisible(false);
		
		StateMenuButton = new JButton("State"); // Bouton pour accéder à la page des
		StateMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		StateMenuButton.setBounds(10, 206, 130, 23);
		StateMenuButton.setVisible(false);

		// Ajout des boutons de navigation au panel principal
		panel.add(comicSearchButton); // ajout du bouton au panel
		panel.add(characterSearchButton); // ajout du bouton au panel
		panel.add(publisherSearchButton); // ajout du bouton au panel
		panel.add(loginButton); // ajout du bouton au panel
		panel.add(registerButton); // ajout du bouton panel
		panel.add(signOutButton); // ajout du bouton au panel
		panel.add(LikedMenuButton); // ajout du bouton menu
		panel.add(StateMenuButton); // ajout du boutton state

		userLabel = new JLabel("");
		userLabel.setEnabled(false);
		userLabel.setBounds(10, 525, 130, 15);
		panel.add(userLabel);

		// LISTENERS:
		MainWindowListener mouselistener = new MainWindowListener(this);
		//
		comicSearchButton.addMouseListener(mouselistener);
		characterSearchButton.addMouseListener(mouselistener);
		publisherSearchButton.addMouseListener(mouselistener);
		loginButton.addMouseListener(mouselistener);
		registerButton.addMouseListener(mouselistener);
		signOutButton.addMouseListener(mouselistener);
		LikedMenuButton.addMouseListener(mouselistener);
		StateMenuButton.addMouseListener(mouselistener);

		// Création des panels de recherche
		comicPanel = new ComicSearchPanel();
		comicPanel.setVisible(false);
		charPanel = new CharacterSearchPanel();
		charPanel.setVisible(false);
		publPanel = new PublisherSearchPanel();
		publPanel.setVisible(false);
		likedPanel = new LikedMenuPanel();
		likedPanel.setVisible(false);
		StatePanel = new StateMenuPanel();
		StatePanel.setVisible(false);
		loginPanel = new LoginPanel(this);
		loginPanel.setVisible(false);
		registerPanel = new RegisterPanel(this);
		registerPanel.setVisible(false);
		// Ajout des panels de recherche au panel principal
		window.getContentPane().add(comicPanel);
		window.getContentPane().add(charPanel);
		window.getContentPane().add(publPanel);
		window.getContentPane().add(likedPanel);
		window.getContentPane().add(StatePanel);
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
		case 0: // Recherche des comics
			comicPanel.setVisible(true);
			loginPanel.setVisible(false);
			charPanel.setVisible(false);
			publPanel.setVisible(false);
			likedPanel.setVisible(false);
			registerPanel.setVisible(false);
			StatePanel.setVisible(false);
			break;
		case 1: // Recherche des perso
			comicPanel.setVisible(false);
			publPanel.setVisible(false);
			loginPanel.setVisible(false);
			likedPanel.setVisible(false);
			registerPanel.setVisible(false);
			charPanel.setVisible(true);
			StatePanel.setVisible(false);
			break;
		case 2: // Login
			comicPanel.setVisible(false);
			charPanel.setVisible(false);
			publPanel.setVisible(false);
			loginPanel.setVisible(true);
			registerPanel.setVisible(false);
			likedPanel.setVisible(false);
			StatePanel.setVisible(false);
			break;
		case 3: // Register
			comicPanel.setVisible(false);
			charPanel.setVisible(false);
			publPanel.setVisible(false);
			loginPanel.setVisible(false);
			registerPanel.setVisible(true);
			likedPanel.setVisible(false);
			StatePanel.setVisible(false);
			break;
		case 4: // Recherche des publishers
			comicPanel.setVisible(false);
			charPanel.setVisible(false);
			publPanel.setVisible(true);
			loginPanel.setVisible(false);
			registerPanel.setVisible(false);
			likedPanel.setVisible(false);
			StatePanel.setVisible(false);
			break;
		case 5: // Recherche des publishers
			comicPanel.setVisible(false);
			charPanel.setVisible(false);
			publPanel.setVisible(false);
			loginPanel.setVisible(false);
			registerPanel.setVisible(false);
			likedPanel.updateResultTable();
			likedPanel.setVisible(true);
			StatePanel.setVisible(false);
			break;
		case 6: // Recherche des publishers
			comicPanel.setVisible(false);
			charPanel.setVisible(false);
			publPanel.setVisible(false);
			loginPanel.setVisible(false);
			registerPanel.setVisible(false);
			likedPanel.setVisible(false);
			StatePanel.updateResultTable();
			StatePanel.setVisible(true);
			break;
		default: // Clear
			comicPanel.setVisible(false);
			charPanel.setVisible(false);
			publPanel.setVisible(false);
			loginPanel.setVisible(false);
			registerPanel.setVisible(false);
			likedPanel.setVisible(false);
			StatePanel.setVisible(false);
		}

	}

	public void setUser(String login, boolean premium) {
		userLabel.setText(login);
		if (premium) {
			userLabel.setText(userLabel.getText() + " - Premium");
		}
		registerButton.setVisible(false);
		loginButton.setVisible(false);
		comicSearchButton.setVisible(true);
		characterSearchButton.setVisible(true);
		publisherSearchButton.setVisible(true);
		signOutButton.setVisible(true);
		LikedMenuButton.setVisible(true);
		StateMenuButton.setVisible(true);
		}

	public void signOut() {
		User.setLogin(null);
		User.setPremium(false);
		userLabel.setText("");
		registerButton.setVisible(true);
		loginButton.setVisible(true);
		comicSearchButton.setVisible(false);
		characterSearchButton.setVisible(false);
		publisherSearchButton.setVisible(false);
		LikedMenuButton.setVisible(false);
		StateMenuButton.setVisible(false);
		setDisplayedPanel(-1);
	}
}