package Listeners;

import DisplayScreen.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class TableListener extends MouseAdapter {

	ComicSearchPanel searchPanel = null;

	public TableListener(ComicSearchPanel searchPanel) {
		this.searchPanel = searchPanel;
	}

	public void mouseClicked(MouseEvent e) {

		int row = searchPanel.getResultTable().rowAtPoint(e.getPoint());
		int col = searchPanel.getResultTable().columnAtPoint(e.getPoint());
		if (col == 4) {
			new Details(searchPanel.getDataList().get(row));
		}
	}

}
