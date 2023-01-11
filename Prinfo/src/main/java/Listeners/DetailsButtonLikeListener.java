package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import DisplayScreen.DetailsComic;

public class DetailsButtonLikeListener extends MouseAdapter{
	DetailsComic detailspanel = null;
	
	@SuppressWarnings("unused")
	private DetailsButtonLikeListener() {
	}
	
	public DetailsButtonLikeListener(DetailsComic details) {
		super();
		this.detailspanel = details;
	}
	
	public void mouseClicked(MouseEvent e) {
		if ((((JButton) e.getSource()).getText()).equals("J'aime")) {
			System.out.println("J'aime bien");
		}
	}
}
