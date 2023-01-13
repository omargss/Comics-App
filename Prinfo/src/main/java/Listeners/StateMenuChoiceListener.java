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

	/**
	 * Constructeur de la classe
	 * 
	 * @param csp : ComicSearchPanel
	 */
	public StateMenuChoiceListener(StateMenuPanel csp) {
		super();
		this.cspanel = csp;
	}

	public void mouseClicked(MouseEvent e) {
		if ((((JButton) e.getSource()).getText()).equals("Read")) {
			System.out.println("Read");
			cspanel.updateResultTable("Read");
		} else if ((((JButton) e.getSource()).getText()).equals("In progress")) {
			System.out.println("In progress");
			cspanel.updateResultTable("In progress");
		} else if ((((JButton) e.getSource()).getText()).equals("Want to read")) {
			System.out.println("Want to read");
			cspanel.updateResultTable("Want to read");
		}
	}
}