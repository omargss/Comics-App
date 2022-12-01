package Affichage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Enumeration;

import javax.swing.*;

import Listeners.Souris;

public class Fenetre{
	JFrame fenetre=null;
	int largeur=800;
	int hauteur=600;
	
	// Objets intéractifs définis dans le constructeur pour les utiliser dans des méthodes externes
	JRadioButton radio_titre = new JRadioButton("Titre",true);
	JRadioButton radio_publieur = new JRadioButton("Publieur");
	JTextField saisie = new JTextField("Recherche");
	JButton rechercher = new JButton("Rechercher");
	JTextArea retour = new JTextArea();
	ButtonGroup ensemble_radio = new ButtonGroup();
	// Méthodes
		// Méthodes liées au bouton "rechercher"
	public JButton get_button() {
		return(rechercher);
	}
	
		// Méthodes liées aux boutons radios
	public String get_radio_value() { // Permet de savoir quel bouton radio est sélectionné
		for(Enumeration<AbstractButton> iterator=ensemble_radio.getElements();iterator.hasMoreElements();) {
			AbstractButton test_bouton=iterator.nextElement();
			//System.out.println(test_bouton.isSelected());
			if(test_bouton.isSelected()) {
				return(test_bouton.getText());
			}
		}
		
		
		return("");
	}
	
		// Méthodes liées à la zone de saisie "titre"
	public JTextField get_area() {
		return(saisie);
	}
	public String get_text() {
		return(saisie.getText());
	}
	public void set_text(String texte) {
		saisie.setText(texte);
	}
	
		// Méthodes liées à la zone d'affichage des résultats
	public void set_results(String resultats) {
		retour.setText(resultats);
	};
	
	
	public Fenetre() {
		// Définition de la fenetre d'affichage
		fenetre=new JFrame(); // permet de définir la var comme nouvelle Frame
		JPanel contentPane = (JPanel) fenetre.getContentPane(); // Définition de la variable ContentPane en raccourci
		fenetre.setVisible(true); // permet de savoir si elle est visible ou non
		fenetre.setPreferredSize(new Dimension(largeur,hauteur)); // dimension de la fenetre
		fenetre.setSize(new Dimension(largeur,hauteur)); // dimension de la fenetre
		fenetre.setLocationRelativeTo(null); // permet de centrer par rapport à un component, si component = null, centre par rapport au bureau
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer le processus quand on quitte la fenetre java
		
		// Panels
			// Zone pour insérer tout objet lié aux recherches
		JPanel recherches = new JPanel(); 
			// Zone pour insérer tout objet lié aux résultats
		JPanel resultats = new JPanel();
		resultats.setVisible(true);
		resultats.setSize(new Dimension(largeur,hauteur-300));
		
		// Listeners
		Souris souris = new Souris(this);
		
		// Objets intéractifs
			// Groupement des boutons radios
		ensemble_radio.add(radio_publieur);
		ensemble_radio.add(radio_titre);
			// Zone de saisie pour la recherche
		saisie.setPreferredSize(new Dimension(200,30));
		saisie.addMouseListener(souris);
			// Bouton de validation de la recherche
		rechercher.addMouseListener(souris);
			// Affichage des résultats
		retour.setEditable(false);
		JScrollPane scroll = new JScrollPane (retour); // Permet de faire défiler l'écran pour voir le reste des résultats
		resultats.add(scroll);
		
		// Ajout dans le panel de la recherche
		recherches.add(saisie);
		recherches.add(radio_titre);
		recherches.add(radio_publieur);
		recherches.add(rechercher);
		
		// Ajout dans le panel principal
		contentPane.add(recherches,BorderLayout.NORTH);
		contentPane.add(scroll,BorderLayout.CENTER);
		fenetre.pack();
	}
}