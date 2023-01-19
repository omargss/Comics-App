package Listeners;

import DisplayScreen.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class StateMenuChoiceListener extends MouseAdapter {

	StateMenuPanel cspanel = null;
	JRadioButton btnRead;
	JRadioButton btnInProgress;
	JRadioButton btnWantToRead;

	/**
	 * Constructeur de la classe
	 * 
	 * @param csp : ComicSearchPanel
	 */
	public StateMenuChoiceListener(StateMenuPanel csp, JRadioButton Read, JRadioButton InProgress,
			JRadioButton WantToRead) {
		super();
		this.cspanel = csp;
		this.btnRead = Read;
		this.btnInProgress = InProgress;
		this.btnWantToRead = WantToRead;

	}

	public void mouseClicked(MouseEvent e) {
		if ((((JRadioButton) e.getSource()).getText()).equals("Read")) {
			if (btnRead.isSelected()) {
				// Deselectionner les autres boutons
				btnInProgress.setSelected(false);
				btnWantToRead.setSelected(false);
				// Update les résultats
				cspanel.updateResultTable("Read");
				cspanel.setSelectedBtn("Read");
			} else if (!btnRead.isSelected()) {
				//Ne pas déselectionner le boutton si il est déjà sélectionné
				btnRead.setSelected(true);
			}
		} else if ((((JRadioButton) e.getSource()).getText()).equals("In progress")) {
			if (btnInProgress.isSelected()) {
				// Deselectionner les autres boutons
				btnRead.setSelected(false);
				btnWantToRead.setSelected(false);
				// Update les résultats
				cspanel.setSelectedBtn("In progress");
				cspanel.updateResultTable("In progress");
			} else if (!btnInProgress.isSelected()) {
				//Ne pas déselectionner le boutton si il est déjà sélectionné
				btnInProgress.setSelected(true);
			}
		} else if ((((JRadioButton) e.getSource()).getText()).equals("Want to read")) {
			if (btnWantToRead.isSelected()) {
				// Deselectionner les autres boutons
				btnRead.setSelected(false);
				btnInProgress.setSelected(false);
				// Update les résultats
				cspanel.updateResultTable("Want to read");
				cspanel.setSelectedBtn("Want to read");
			} else if (!btnWantToRead.isSelected()) {
				//Ne pas déselectionner le boutton si il est déjà sélectionné
				btnWantToRead.setSelected(true);
			}
		}
	}
}