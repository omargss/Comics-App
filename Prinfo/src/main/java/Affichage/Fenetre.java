package Affichage;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import Listeners.Souris;

public class Fenetre{
	JFrame fenetre=null;
	int largeur=800;
	int hauteur=600;

	JTextField saisie = new JTextField("Titre");
	JButton rechercher = new JButton("Rechercher");
	
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
		
		// Listeners
		Souris souris = new Souris(this);
		
		// Objets intéractifs
		saisie.setPreferredSize(new Dimension(200,30));
		saisie.addMouseListener(souris);
		rechercher.addMouseListener(souris);
		
		
		
		// Ajout dans le panel
		recherches.add(saisie);
		recherches.add(rechercher);
		
		// Ajout dans le panel principal
		contentPane.add(recherches);
		fenetre.pack();
	}
}