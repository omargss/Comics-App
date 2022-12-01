package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Affichage.Fenetre;
import GetData.DisplayData;

public class Souris extends MouseAdapter {
	Fenetre ecran = null;

	@SuppressWarnings("unused")
	private Souris() {
	}

	public Souris(Fenetre fenetre) {
		super();
		this.ecran = fenetre;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			if(e.getSource() == ecran.get_button()) {
			ecran.set_results(DisplayData.DisplayDate(ecran.get_text()));
			ecran.set_text("");
		}
		else if(e.getSource()==ecran.get_area()) {
			ecran.set_text("");
		}
	}
}