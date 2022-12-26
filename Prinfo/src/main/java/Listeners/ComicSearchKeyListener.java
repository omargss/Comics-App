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

	public ComicSearchKeyListener(ComicSearchPanel csp) {
		super();
		this.cspanel = csp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			List<Comic> dataList = null;
            String search = cspanel.getTextField().getText();
            String yearMin = cspanel.getDropDownYearsMin();
            String yearMax = cspanel.getDropDownYearsMax();

			String sort = cspanel.getDropDownSortFieldChoice() + ":" + cspanel.getDropDownSortOrder(); // ex : title:asc
            String titleOrPublisherChoice = cspanel.getRadioValue();
            // ce string renvoie "Title" ou "Publisher" selon le choix
            if (cspanel.getRadioValue().equals("Title")) {
				dataList=GetComicsData.getComicsDataByName(search,null,null,yearMin,yearMax);
            } else {
				dataList=GetComicsData.getComicsDataByPublisher(search,null,null,yearMin,yearMax);
            }
            cspanel.updateResultTable(dataList);
		}
	}
}