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
			System.out.println("Mode 0");
			System.out.println("Affichage de la recherche par comic");
		}
		if ((((JButton) e.getSource()).getText()).equals("Character search")) {
			this.window.setDisplayedPanel(1);
			System.out.println("Mode 0");
			System.out.println("Affichage de la recherche par personnage");
		}
		if ((((JButton) e.getSource()).getText()).equals("Login")) {
			this.window.setDisplayedPanel(2);
			System.out.println("Mode 2");
			System.out.println("Affichage de la page de connexion");
		}
	}

}