package DisplayScreen;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import Listeners.*;


public class MainWindow{
	JFrame window=null;
	int width=1000;
	int height=600;
	ComicSearchPanel comicPanel;

	
	public MainWindow() {
		// Définition de la fenetre d'affichage
		window=new JFrame(); // permet de définir la var comme nouvelle Frame
		window.setVisible(true); // permet de savoir si elle est visible ou non
		window.setPreferredSize(new Dimension(width,height)); // dimension de la fenetre
		window.setSize(new Dimension(width,height)); // dimension de la fenetre
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer le processus quand on quitte la fenetre java
		window.pack();
		window.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 150, 600);
		panel.setVisible(true);
		window.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton comicSearchButton = new JButton("Comic search");
		panel.add(comicSearchButton);
		
		JButton characterSearchButton = new JButton("Character search");
		panel.add(characterSearchButton);
		
		JButton loginButton = new JButton("Login");
		panel.add(loginButton);
		// LISTENERS:
		MainWindowListener mouselistener = new MainWindowListener(this);
		//
		comicSearchButton.addMouseListener(mouselistener);
		characterSearchButton.addMouseListener(mouselistener);
		loginButton.addMouseListener(mouselistener);
		
		comicPanel = new ComicSearchPanel();
		comicPanel.setVisible(false);
		window.getContentPane().add(comicPanel);
	}


	public void setDisplayedPanel(int i) {
		switch (i) {
		case 0:
			comicPanel.setVisible(true);
			break;
		case 1:
			comicPanel.setVisible(false);
		default:
			break;
		}
		
	}
}