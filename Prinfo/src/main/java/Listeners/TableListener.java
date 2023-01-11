package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import DisplayScreen.CharacterSearchPanel;
import DisplayScreen.ComicSearchPanel;
import DisplayScreen.DetailsCharacter;
import DisplayScreen.DetailsComic;
import DisplayScreen.DetailsPublisher;
import DisplayScreen.PublisherSearchPanel;
import Objects.Comic;
import Objects.Publisher;
import Objects.Character;

public class TableListener extends MouseAdapter {

	JPanel searchPanel = null;
	JFrame details = null;

	public TableListener(JPanel searchPanel) {
		this.searchPanel = searchPanel;
	}

	public void mouseClicked(MouseEvent e) {

		if (searchPanel instanceof ComicSearchPanel) {
			System.out.println("Comic");
			int row = ((ComicSearchPanel) searchPanel).getResultTable().rowAtPoint(e.getPoint());
			int col = ((ComicSearchPanel) searchPanel).getResultTable().columnAtPoint(e.getPoint());
			//System.out.println(row);
			if (col == 4) {
				System.out.println("Détails comic");
				Comic comic = ((ComicSearchPanel) searchPanel).getDataList().get(row);
				details = new DetailsComic(comic);
			}
		} else if (searchPanel instanceof CharacterSearchPanel) {
			System.out.println("Character");
			int row = ((CharacterSearchPanel) searchPanel).getResultTable().rowAtPoint(e.getPoint());
			int col = ((CharacterSearchPanel) searchPanel).getResultTable().columnAtPoint(e.getPoint());
			//System.out.println(row);
			if (col == 2) {
				System.out.println("Détails perso");
				Character character = ((CharacterSearchPanel) searchPanel).getDataList().get(row);
				details = new DetailsCharacter(character);
			}
		} else if (searchPanel instanceof PublisherSearchPanel) {
			System.out.println("Publisher");
			int row = ((PublisherSearchPanel) searchPanel).getResultTable().rowAtPoint(e.getPoint());
			int col = ((PublisherSearchPanel) searchPanel).getResultTable().columnAtPoint(e.getPoint());
			//System.out.println(row);
			if (col == 1) {
				System.out.println("Détails publisher");
				Publisher publisher = ((PublisherSearchPanel) searchPanel).getDataList().get(row);
				details = new DetailsPublisher(publisher);
			}
		}
		/*
		 * int row = searchPanel.getResultTable().rowAtPoint(e.getPoint()); int col =
		 * searchPanel.getResultTable().columnAtPoint(e.getPoint()); if (col == 4) {
		 * System.out.println(row); if(searchPanel.getDataList().get(row) instanceof
		 * Comic) { System.out.println("Comics"); } else
		 * if(searchPanel.getDataList().get(row) instanceof Publisher) {
		 * System.out.println("Publisher"); } else if(searchPanel.getDataList().get(row)
		 * instanceof Character) { System.out.println("Character"); } Details details =
		 * new Details(searchPanel.getDataList().get(row)); }
		 */
	}

}