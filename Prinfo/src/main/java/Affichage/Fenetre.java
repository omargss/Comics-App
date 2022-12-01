package Affichage;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import Listeners.Keyboard;
import Listeners.Souris;

public class Fenetre{
	JFrame fenetre=null;
	int largeur=800;
	int hauteur=600;

	JTextField saisie = new JTextField("");
	JButton rechercher = new JButton("Rechercher");
	JTextArea retour = new JTextArea();
	
	public JButton get_button() {
		return(rechercher);
	}
	public JTextField get_area() {
		return(saisie);
	}
	public String get_text() {
		return(saisie.getText());
	}
	public void set_text(String texte) {
		saisie.setText(texte);
	}
	
	public void set_results(String resultats) {
		retour.setText(resultats);
	};
	
	public Fenetre() {
		fenetre=new JFrame(); // permet de définir la var comme nouvelle Frame
		JPanel contentPane = (JPanel) fenetre.getContentPane();
		fenetre.setVisible(true); // permet de savoir si elle est visible ou non
		fenetre.setPreferredSize(new Dimension(largeur,hauteur));
		fenetre.setSize(new Dimension(largeur,hauteur));
		fenetre.setLocationRelativeTo(null); // permet de centrer par rapport à un component, si component = null, centre par rapport au bureau
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer le processus quand on quitte la fenetre java
		
		// Panels
		JPanel recherches = new JPanel();
		JPanel resultats = new JPanel();
		resultats.setVisible(true);
		resultats.setSize(new Dimension(largeur,hauteur-300));
		
		// Listeners
		Souris souris = new Souris(this);
		Keyboard keyboard = new Keyboard(this);
		saisie.setToolTipText("Titre");
		// Objets intéractifs
		saisie.setPreferredSize(new Dimension(200,30));
		saisie.addKeyListener(keyboard);
		rechercher.addMouseListener(souris);
		retour.setEditable(false);
		JScrollPane scroll = new JScrollPane (retour);
		JScrollPane scroll2 = new JScrollPane (retour);

		resultats.add(scroll);
		
		// Ajout dans le panel
		recherches.add(saisie);
		recherches.add(rechercher);
		
		// Ajout dans le panel principal
		contentPane.add(recherches,BorderLayout.NORTH);
		contentPane.add(scroll2,BorderLayout.CENTER);
		fenetre.pack();
	}
}