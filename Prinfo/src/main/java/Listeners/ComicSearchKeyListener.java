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
			String yearMin = cspanel.getDropDownYearsMin();
			String yearMax = cspanel.getDropDownYearsMax();
			String limit = "";

			String sort = cspanel.getDropDownSortFieldChoice() + ":" + cspanel.getDropDownSortOrder(); // ex : title:asc

			System.out.println(sort);
			
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
			
			String titleOrPublisherChoice = cspanel.getRadioValue(); // ce string renvoie "Title" ou "Publisher" selon le choix
			switch(titleOrPublisherChoice) {
			case "Title":
				dataList = GetComicsData.getComicsDataByName(search, null, limit, yearMin, yearMax);
				break;
			case "Publisher":
				dataList = GetComicsData.getComicsDataByPublisher(search, null, limit, yearMin, yearMax);
				break;
			/*case "Author":
				dataList = GetComicsData.getComicsDataByAuthor(search, null, null, yearMin, yearMax);
				break;*/
			}
			cspanel.updateResultTable(dataList); // On met à jour
		}
	}
}