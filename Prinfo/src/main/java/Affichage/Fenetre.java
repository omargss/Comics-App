package Affichage;
import java.awt.Dimension;
import java.util.List;

import javax.swing.*;

import GetData.GetComicsData;
import Listeners.Keyboard;
import Listeners.Souris;
import Objects.Comic;

public class Fenetre{
	JFrame fenetre=null;
	int largeur=800;
	int hauteur=600;
	JPanel recherches = new JPanel();
	JPanel resultats = new JPanel();

	JTextField saisie = new JTextField("");
	JButton rechercher = new JButton("Rechercher");
	private JTable table;

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
	public void set_results() {

		String[] columnNames = {"Titre", "date", "volume","Access page"};
		List<Comic> listeData = GetComicsData.getComicsData(saisie.getText(), null, null);
		String[][] list = new String[listeData.size()][4];
		for(int i = 0; i< listeData.size(); i++) {
			list[i][0] = listeData.get(i).getName();
			list[i][1] = listeData.get(i).getDate();
			list[i][2] = listeData.get(i).getVolume();
			list[i][3] = "Details";
		}
		table = new JTable(list,columnNames);
		table.setEnabled(false);
		table.setBounds(0, 60, 800, 240);;
		resultats.add(new JScrollPane(table));
		table.setFillsViewportHeight(true);
		JScrollBar scrollBar = new JScrollBar();
		table.add(scrollBar);
		fenetre.getContentPane().add(table);
		fenetre.pack();

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
		recherches.setBounds(0, 0, 786, 40);
		resultats.setLocation(0, 50);
		resultats.setVisible(true);
		resultats.setSize(new Dimension(800, 250));
		
		// Listeners
		Souris souris = new Souris(this);
		Keyboard keyboard = new Keyboard(this);
		saisie.setToolTipText("Titre");
		// Objets intéractifs
		saisie.setPreferredSize(new Dimension(200,30));
		saisie.addKeyListener(keyboard);
		rechercher.addMouseListener(souris);
		fenetre.getContentPane().setLayout(null);
		
		// Ajout dans le panel
		recherches.add(saisie);
		recherches.add(rechercher);
		
		// Ajout dans le panel principal
		contentPane.add(recherches);
		contentPane.add(resultats);
			}
}