package Listeners;

import DisplayScreen.*;
import GetData.GetCharactersData;
import Objects.Character;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

public class CharacterSearchListener extends MouseAdapter {

	CharacterSearchPanel cspanel = null;

	public CharacterSearchListener(CharacterSearchPanel csp) {
		super();
		cspanel = csp;
	}

	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o instanceof JButton) {
			JButton b = (JButton) o;
			// Si on clique sur le bouton de recherche
			if (b.getText().equals("Search")) {
				List<Character> dataList = null;
	            String search = cspanel.getTextField().getText();
				dataList=GetCharactersData.GetCharacterData(search);
	            cspanel.updateResults(dataList);
			}
		}
	}
}