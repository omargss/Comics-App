package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import DisplayScreen.Details;
import DisplayScreen.MainWindow;

public class DetailsButtonsListener extends MouseAdapter{
	Details detailspanel = null;
	
	@SuppressWarnings("unused")
	private DetailsButtonsListener() {
	}
	
	public DetailsButtonsListener(Details details) {
		super();
		this.detailspanel = details;
	}
	
	public void mouseClicked(MouseEvent e) {
		if ((((JButton) e.getSource()).getText()).equals("Lu")) {
			System.out.println("LU !!!");
		}
		
		if ((((JButton) e.getSource()).getText()).equals("Envie de lire")) {
			System.out.println("J'ai faim de lecture xD");
		}
		
		if ((((JButton) e.getSource()).getText()).equals("En cours de lecture")) {
			System.out.println("En cours...");
		}
	}
}
