package Listeners;

import DisplayScreen.*;
import GetData.GetComicsData;
import Objects.Comic;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

public class ComicSearchListener extends MouseAdapter {

	ComicSearchPanel cspanel = null;

	/**
	 * Constructeur de la classe
	 * 
	 * @param csp : ComicSearchPanel
	 */
	public ComicSearchListener(ComicSearchPanel csp) {
		super();
		this.cspanel = csp;
	}

	public void mouseClicked(MouseEvent e) {
		List<Comic> dataList = null; // Liste de comics permettant de récupérer les comics que l'on va afficher
		Object o = e.getSource(); // On récupère l'objet sur lequel l'utilisateur a cliqué
		if (o instanceof JButton) { // S'il s'agit d'un JButton alors on peut continuer
			JButton b = (JButton) o;
			// Si on clique sur le bouton de recherche
			if (b.getText().equals("Search")) {
				String search = this.cspanel.getTextField().getText();
				String yearMin = this.cspanel.getDropDownYearsMin();
				String yearMax = this.cspanel.getDropDownYearsMax();
				String sort = "";
				// Permet de définir le type de trie que l'on souhaite faire, c'est-à-dire tri
				// selon la date ou le nom
				if (cspanel.getDropDownSortFieldChoice().equals("null")) { // Si on a choisi aucun tri
					sort = null;
				} else if (this.cspanel.getDropDownSortFieldChoice().equals("date")
						&& this.cspanel.getRadioValue().equals("Title")) { // Si on a choisi le tri par date et qu'on
																			// recherche par titre
					sort = "cover_date:" + this.cspanel.getDropDownSortOrder();
				} else if (this.cspanel.getDropDownSortFieldChoice().equals("date")
						&& this.cspanel.getRadioValue().equals("Publisher")) { // Si on a choisi le tri par date et
																				// qu'on
																				// recherche par publieur
					sort = "start_year:" + this.cspanel.getDropDownSortOrder();
				} else {
					sort = "name:" + this.cspanel.getDropDownSortOrder();
				}
				System.out.println(sort);

				String titleOrPublisherChoice = this.cspanel.getRadioValue();
				// ce string renvoie "Title" ou "Publisher" selon le choix
				if (titleOrPublisherChoice.equals("Title")) { // Si on recherche par titre
					dataList = GetComicsData.getComicsDataByName(search, sort, null, yearMin, yearMax);
				} else { // Si on recherche par publieur
					dataList = GetComicsData.getComicsDataByPublisher(search, sort, null, yearMin, yearMax);
				}
				this.cspanel.updateResultTable(dataList); // On met à jour
			}
		}
	}
}