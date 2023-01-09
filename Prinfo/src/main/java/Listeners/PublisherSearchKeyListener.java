package Listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import DisplayScreen.PublisherSearchPanel;
import GetData.GetPublishersData;
import Objects.Publisher;

public class PublisherSearchKeyListener extends KeyAdapter {

	PublisherSearchPanel pspanel = null;

	/**
	 * Constructeur de la classe
	 * 
	 * @param csp : ComicSearchPanel
	 */
	public PublisherSearchKeyListener(PublisherSearchPanel psp) {
		super();
		this.pspanel = psp;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Si on appuye sur la touche entrée
			List<Publisher> dataList = null;
			String name = pspanel.getTextField().getText();
			dataList = GetPublishersData.getPublishers(name);
			this.pspanel.updateResultTable(dataList);
		}
	}
}