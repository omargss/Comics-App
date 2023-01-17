package Listeners;

import DisplayScreen.*;
import GetData.GetCharactersData;
import Objects.Character;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class CharacterSearchKeyListener extends KeyAdapter {

	CharacterSearchPanel cspanel = null;

	/**
	 * Constructeur de la classe
	 * 
	 * @param csp : ComicSearchPanel
	 */
	public CharacterSearchKeyListener(CharacterSearchPanel csp) {
		super();
		this.cspanel = csp;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Si on appuye sur la touche entrée
			List<Character> dataList = null;
			String name = cspanel.getTextField().getText();
			dataList = GetCharactersData.getCharacters(name);
			this.cspanel.updateResultTable(dataList);
		}
	}
}