package DisplayScreen;

import Listeners.CharacterSearchKeyListener;
import Listeners.CharacterSearchListener;
import Objects.Character;

import javax.swing.*;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class CharacterSearchPanel extends JPanel {
	public JTextField getTextField() {
		return textField;
	}
	private JTextField textField;
	private JButton search;
	private JPanel panel = new JPanel();
	// METHOD that changes the results of the JTable
	public void updateResults(List<Character> dataList) {
		remove(panel);
		panel = new JPanel();
		for(int i=0;i<dataList.size();i++) {
			JButton test = new JButton(dataList.get(i).getName());
			panel.add(test);
		}
		panel.setBounds(50, 100, 575, 500);
		panel.setLayout(new GridLayout(5,3));
		add(panel);
		
	}

	public CharacterSearchPanel() {
		setBounds(150,0,1000,600);
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(417, 6, 96, 20);
		add(textField);
		textField.setColumns(10);

		search = new JButton("Search");
		search.setBounds(550, 5, 100, 23);
		add(search);


		// Listeners
		CharacterSearchListener csl = new CharacterSearchListener(this);
		search.addMouseListener(csl);
		CharacterSearchKeyListener cskl = new CharacterSearchKeyListener(this);
		textField.addKeyListener(cskl);
	}
}

