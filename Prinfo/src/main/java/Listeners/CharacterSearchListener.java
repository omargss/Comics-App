package Listeners;

import DisplayScreen.*;
import GetData.GetCharactersData;
import Objects.Character;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

public class CharacterSearchListener extends MouseAdapter {

	CharacterSearchPanel cspanel = null;

	/**
	 * Constructeur de la classe
	 * 
	 * @param csp : ComicSearchPanel
	 */
	public CharacterSearchListener(CharacterSearchPanel csp) {
		super();
		this.cspanel = csp;
	}

	public void mouseClicked(MouseEvent e) {
		List<Character> dataList = null;
		Object o = e.getSource(); // On récupère l'objet sur lequel l'utilisateur a cliqué
		if (o instanceof JButton) { // S'il s'agit d'un JButton alors on peut continuer
			JButton b = (JButton) o;
			// Si on clique sur le bouton de recherche
			if (b.getText().equals("Search")) {
				String name = cspanel.getTextField().getText();
				// GetCharactersData.getCharacters(name);
				dataList = GetCharactersData.getCharacters(name);
				this.cspanel.updateResultTable(dataList);
			}
		}
	}
}