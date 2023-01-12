package Listeners;

import DisplayScreen.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MainWindowListener extends MouseAdapter {
	MainWindow window = null;

	@SuppressWarnings("unused")
	private MainWindowListener() {
	}

	/**
	 * Constructeur de la classe
	 * 
	 * @param w : MainWindow
	 */
	public MainWindowListener(MainWindow window) {
		super();
		this.window = window;
	}

	public void mouseClicked(MouseEvent e) {
		if ((((JButton) e.getSource()).getText()).equals("Comic search")) {
			this.window.setDisplayedPanel(0);
			//System.out.println("Mode 0");
			//System.out.println("Affichage de la recherche par comic");
		}
		if ((((JButton) e.getSource()).getText()).equals("Character search")) {
			this.window.setDisplayedPanel(1);
			//System.out.println("Mode 0");
			//System.out.println("Affichage de la recherche par personnage");
		}
		if ((((JButton) e.getSource()).getText()).equals("Login")) {
			this.window.setDisplayedPanel(2);
			//System.out.println("Mode 2");
			//System.out.println("Affichage de la page de connexion");
		}
		if ((((JButton) e.getSource()).getText()).equals("Register")) {
			this.window.setDisplayedPanel(3);
			// System.out.println("Mode 3");
			//System.out.println("Affichage de la page d'enregistrement");
		}
		if ((((JButton) e.getSource()).getText()).equals("Sign out")) {
			this.window.signOut();
		}
		if ((((JButton) e.getSource()).getText()).equals("Publisher search")) {
			this.window.setDisplayedPanel(4);
		}
		if ((((JButton) e.getSource()).getText()).equals("Liked")) {
			this.window.setDisplayedPanel(5);
		}
		
	}

}