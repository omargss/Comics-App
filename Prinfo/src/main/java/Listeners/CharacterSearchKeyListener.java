package Listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import DisplayScreen.CharacterSearchPanel;
import GetData.GetCharactersData;
import Objects.Character;

public class CharacterSearchKeyListener extends KeyAdapter {
	CharacterSearchPanel cspanel = null;

	@SuppressWarnings("unused")
	private CharacterSearchKeyListener() {
	}

	public CharacterSearchKeyListener(CharacterSearchPanel csp) {
		super();
		this.cspanel = csp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			List<Character> dataList = null;
            String search = cspanel.getTextField().getText();
			dataList=GetCharactersData.GetCharacterData(search);
            cspanel.updateResults(dataList);
		}
	}
}