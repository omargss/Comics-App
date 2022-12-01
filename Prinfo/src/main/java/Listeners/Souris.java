package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import Affichage.Fenetre;
import GetData.DisplayData;
import GetData.GetComicsData;
import Objects.Comic;

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
				String filtre = ecran.get_radio_value();
				String recherche = ecran.get_text();
				List<Comic> listeData = null;
				switch(filtre) {
				case "Titre":
					listeData=GetComicsData.getComicsData(recherche, null, null, null);
					break;
				case "Publieur":
					listeData=GetComicsData.getComicsData(null, recherche, null, null);
					break;
				}
				ecran.set_results(DisplayData.Display(listeData));
				ecran.set_text("");
		}
		else if(e.getSource()==ecran.get_area()) {
			ecran.set_text("");
		}
	}
}