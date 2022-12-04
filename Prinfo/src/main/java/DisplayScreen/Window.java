package DisplayScreen;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Enumeration;

import javax.swing.*;

import Listeners.Mouse;

public class Window{
	JFrame window=null;
	int width=800;
	int height=600;
	
	// Objets intéractifs définis dans le constructeur pour les utiliser dans des méthodes externes
	JRadioButton radioTitle = new JRadioButton("Title",true);
	JRadioButton radioPublisher = new JRadioButton("Publisher");
	JTextField entry = new JTextField("Recherche");
	JButton search = new JButton("Search");
	JTextArea APIReturn = new JTextArea();
	ButtonGroup radioGroup = new ButtonGroup();
	// Méthodes
		// Méthodes liées au bouton "rechercher"
	public JButton get_button() {
		return(search);
	}
	
		// Méthodes liées aux boutons radios
	public String get_radio_value() { // Permet de savoir quel bouton radio est sélectionné
		for(Enumeration<AbstractButton> iterator=radioGroup.getElements();iterator.hasMoreElements();) {
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
		return(entry);
	}
	public String get_text() {
		return(entry.getText());
	}
	public void set_text(String text) {
		entry.setText(text);
	}
	
		// Méthodes liées à la zone d'affichage des résultats
	public void set_results(String results) {
		APIReturn.setText(results);
	};
	
	
	public Window() {
		// Définition de la fenetre d'affichage
		window=new JFrame(); // permet de définir la var comme nouvelle Frame
		JPanel contentPane = (JPanel) window.getContentPane(); // Définition de la variable ContentPane en raccourci
		window.setVisible(true); // permet de savoir si elle est visible ou non
		window.setPreferredSize(new Dimension(width,height)); // dimension de la fenetre
		window.setSize(new Dimension(width,height)); // dimension de la fenetre
		window.setLocationRelativeTo(null); // permet de centrer par rapport à un component, si component = null, centre par rapport au bureau
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer le processus quand on quitte la fenetre java
		
		// Panels
			// Zone pour insérer tout objet lié aux recherches
		JPanel searches = new JPanel(); 
			// Zone pour insérer tout objet lié aux résultats
		JPanel results = new JPanel();
		results.setVisible(true);
		results.setSize(new Dimension(width,height-300));
		
		// Listeners
		Mouse mouse = new Mouse(this);
		
		// Objets intéractifs
			// Groupement des boutons radios
		radioGroup.add(radioPublisher);
		radioGroup.add(radioTitle);
			// Zone de saisie pour la recherche
		entry.setPreferredSize(new Dimension(200,30));
		entry.addMouseListener(mouse);
			// Bouton de validation de la recherche
		searches.addMouseListener(mouse);
			// Affichage des résultats
		APIReturn.setEditable(false);
		JScrollPane scroll = new JScrollPane (APIReturn); // Permet de faire défiler l'écran pour voir le reste des résultats
		results.add(scroll);
		
		// Ajout dans le panel de la recherche
		searches.add(entry);
		searches.add(radioTitle);
		searches.add(radioPublisher);
		searches.add(searches);
		
		// Ajout dans le panel principal
		contentPane.add(searches,BorderLayout.NORTH);
		contentPane.add(scroll,BorderLayout.CENTER);
		window.pack();
	}
}