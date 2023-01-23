package Listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import DisplayScreen.ComicSearchPanel;
import GetData.GetComicsData;
import Objects.Comic;

public class ComicSearchKeyListener extends KeyAdapter {
	ComicSearchPanel cspanel = null;

	@SuppressWarnings("unused")
	private ComicSearchKeyListener() {
	}

	/**
	 * Constructeur de la classe
	 * 
	 * @param csp : ComicSearchPanel
	 */
	public ComicSearchKeyListener(ComicSearchPanel csp) {
		super();
		this.cspanel = csp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Si on appuye sur la touche entrée
			List<Comic> dataList = null;
			String search = cspanel.getTextField().getText();
			if (search.equals("")) {
				System.out.println("enter smtg");
				return;
			} else {
				String yearMin = this.cspanel.getDropDownYearsMin();
				String yearMax = this.cspanel.getDropDownYearsMax();
				String sort = "";
				String limit = "";
				// Permet de définir le type de trie que l'on souhaite faire, c'est-à-dire tri
				// selon la date ou le nom
				if (cspanel.getDropDownSortFieldChoice().equals("null")) { // Si on a choisi aucun tri
					sort = null;
				} else if (this.cspanel.getDropDownSortFieldChoice().equals("date")
						&& this.cspanel.getRadioValue().equals("Title")) { // Si on a choisi le tri par date et
																			// qu'on
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
				String resultsNumber = this.cspanel.getDropItem();
				switch (resultsNumber) {
					case "All results":
						limit = "null";
						break;
					case "5 items":
						limit = "5";
						break;
					case "10 items":
						limit = "10";
						break;
					case "25 items":
						limit = "25";
						break;
					case "50 items":
						limit = "50";
						break;
					case "100 items":
						limit = "100";
						break;

				}
				System.out.println(sort);

				String titleOrPublisherChoice = this.cspanel.getRadioValue();
				// ce string renvoie "Title" ou "Publisher" selon le choix
				if (titleOrPublisherChoice.equals("Title")) { // Si on recherche par titre
					dataList = GetComicsData.getComicsDataByName(search, sort, limit, yearMin, yearMax);
				} else { // Si on recherche par publieur
					dataList = GetComicsData.getComicsDataByPublisher(search, sort, limit, yearMin, yearMax);
				}
				this.cspanel.updateResultTable(dataList); // On met à jour
			}

		}
	}
}