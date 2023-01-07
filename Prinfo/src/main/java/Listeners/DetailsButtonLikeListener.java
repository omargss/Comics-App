package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import DisplayScreen.Details;
import DisplayScreen.MainWindow;

public class DetailsButtonLikeListener extends MouseAdapter{
	Details detailspanel = null;
	
	@SuppressWarnings("unused")
	private DetailsButtonLikeListener() {
	}
	
	public DetailsButtonLikeListener(Details details) {
		super();
		this.detailspanel = details;
	}
	
	public void mouseClicked(MouseEvent e) {
		if ((((JButton) e.getSource()).getText()).equals("J'aime")) {
			System.out.println("J'aime bien");
		}
	}
}
