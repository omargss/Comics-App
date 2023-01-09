package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;

import DisplayScreen.PublisherSearchPanel;
import GetData.GetPublishersData;
import Objects.Publisher;

public class PublisherSearchListener extends MouseAdapter {

	PublisherSearchPanel pspanel = null;

	/**
	 * Constructeur de la classe
	 * 
	 * @param csp : ComicSearchPanel
	 */
	public PublisherSearchListener(PublisherSearchPanel psp) {
		super();
		this.pspanel = psp;
	}

	public void mouseClicked(MouseEvent e) {
		List<Publisher> dataList = null;
		Object o = e.getSource(); // On récupère l'objet sur lequel l'utilisateur a cliqué
		if (o instanceof JButton) { // S'il s'agit d'un JButton alors on peut continuer
			JButton b = (JButton) o;
			// Si on clique sur le bouton de recherche
			if (b.getText().equals("Search")) {
				String name = pspanel.getTextField().getText();
				// GetCharactersData.getCharacters(name);
				dataList = GetPublishersData.getPublishers(name);
				this.pspanel.updateResultTable(dataList);
			}
		}
	}
}