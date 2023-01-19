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
import DisplayScreen.LikedMenuPanel;
import DisplayScreen.PublisherSearchPanel;
import DisplayScreen.StateMenuPanel;
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
			// System.out.println("Comic");
			int row = ((ComicSearchPanel) searchPanel).getResultTable().rowAtPoint(e.getPoint());
			int col = ((ComicSearchPanel) searchPanel).getResultTable().columnAtPoint(e.getPoint());
			// System.out.println(row);
			if (col == 4) {
				// System.out.println("Détails comic");
				Comic comic = ((ComicSearchPanel) searchPanel).getDataList().get(row);
				details = new DetailsComic(comic);
			}
		} else if (searchPanel instanceof LikedMenuPanel) { // Bouton détail dans le menu liked
			int row = ((LikedMenuPanel) searchPanel).getResultTable().rowAtPoint(e.getPoint());
			int col = ((LikedMenuPanel) searchPanel).getResultTable().columnAtPoint(e.getPoint());
			// System.out.println(row);
			if (col == 4) {
				// System.out.println("Détails comic");
				Comic comic = ((LikedMenuPanel) searchPanel).getDataList().get(row);
				details = new DetailsComic(comic);
			}
			if (col == 5) {
				Comic comic = ((LikedMenuPanel) searchPanel).getDataList().get(row);
				((LikedMenuPanel) searchPanel).deleteLikedComic(comic.getIssue());
			}
		} else if (searchPanel instanceof StateMenuPanel) {
			int row = ((StateMenuPanel) searchPanel).getResultTable().rowAtPoint(e.getPoint());
			int col = ((StateMenuPanel) searchPanel).getResultTable().columnAtPoint(e.getPoint());
			// System.out.println(row);
			if (col == 4) {
				// System.out.println("Détails comic");
				Comic comic = ((StateMenuPanel) searchPanel).getDataList().get(row);
				details = new DetailsComic(comic);
			}
			if (col == 5) {
				Comic comic = ((StateMenuPanel) searchPanel).getDataList().get(row);
				((StateMenuPanel) searchPanel).deleteComic(comic.getIssue());
			}
		} else if (searchPanel instanceof CharacterSearchPanel) {
			// System.out.println("Character");
			int row = ((CharacterSearchPanel) searchPanel).getResultTable().rowAtPoint(e.getPoint());
			int col = ((CharacterSearchPanel) searchPanel).getResultTable().columnAtPoint(e.getPoint());
			// System.out.println(row);
			if (col == 2) {
				// System.out.println("Détails perso");
				Character character = ((CharacterSearchPanel) searchPanel).getDataList().get(row);
				details = new DetailsCharacter(character);
			}
		} else if (searchPanel instanceof PublisherSearchPanel) {
			// System.out.println("Publisher");
			int row = ((PublisherSearchPanel) searchPanel).getResultTable().rowAtPoint(e.getPoint());
			int col = ((PublisherSearchPanel) searchPanel).getResultTable().columnAtPoint(e.getPoint());
			// System.out.println(row);
			if (col == 1) {
				// System.out.println("Détails publisher");
				Publisher publisher = ((PublisherSearchPanel) searchPanel).getDataList().get(row);
				details = new DetailsPublisher(publisher);
			}
		}
	}

}
