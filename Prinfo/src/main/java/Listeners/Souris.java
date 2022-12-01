package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import Affichage.Fenetre;
import GetData.DisplayData;
import GetData.GetComicsData;

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
		//System.out.println(e);
		if(e.getSource() == ecran.get_button()) {
			//System.out.println(e);
			//System.out.println(ecran.get_text());
			//GetComicsData.getComicsData(ecran.get_text(),null,null);
			ecran.set_results(DisplayData.DisplayDate(ecran.get_text()));
			ecran.set_text("");
			/*
			 * Réussir à implémenter la fonction de focus et unfocus pour enlever le texte dessus
			 * */
		}
		else if(e.getSource()==ecran.get_area()) {
			//System.out.println(e);
			ecran.set_text("");
		}
	}
}