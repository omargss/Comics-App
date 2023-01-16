package Listeners;

import DisplayScreen.*;
import GetData.GetComicsData;
import Objects.Comic;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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
				System.out.println("Read");
				// Deselectionner les autres boutons
				btnInProgress.setSelected(false);
				btnWantToRead.setSelected(false);
				// Update les résultats
				cspanel.updateResultTable("Read");
			} else if (!btnRead.isSelected()) {
				//Ne pas déselectionner le boutton si il est déjà sélectionné
				btnRead.setSelected(true);
			}
		} else if ((((JRadioButton) e.getSource()).getText()).equals("In progress")) {
			if (btnInProgress.isSelected()) {
				System.out.println("In progress");
				// Deselectionner les autres boutons
				btnRead.setSelected(false);
				btnWantToRead.setSelected(false);
				// Update les résultats
				cspanel.updateResultTable("In progress");
			} else if (!btnInProgress.isSelected()) {
				//Ne pas déselectionner le boutton si il est déjà sélectionné
				btnInProgress.setSelected(true);
			}
		} else if ((((JRadioButton) e.getSource()).getText()).equals("Want to read")) {
			if (btnWantToRead.isSelected()) {
				System.out.println("Want to read");
				// Deselectionner les autres boutons
				btnRead.setSelected(false);
				btnInProgress.setSelected(false);
				// Update les résultats
				cspanel.updateResultTable("Want to read");
			} else if (!btnWantToRead.isSelected()) {
				//Ne pas déselectionner le boutton si il est déjà sélectionné
				btnWantToRead.setSelected(true);
			}
		}
	}
}