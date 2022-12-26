package DisplayScreen;

import Objects.Character;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

import Listeners.CharacterSearchListener;

import java.awt.BorderLayout;
import java.util.List;

/**
 * Classe permettant de créer le panel permettant de faire des recherches de
 * personnages
 *
 */
public class CharacterSearchPanel extends JPanel {

	//private JTable resultTable = new JTable();
	private JTextField textField;
	private JButton search;
	private JTable resultTable = new JTable();

	/**
	 * Permet de retourner le contenu du champ de saisie pour les recherches
	 * 
	 * @return JTextField textField
	 */
	public JTextField getTextField() {
		return textField;
	}
	
	public void updateResultTable(List<Character> dataList) {
		String[] columnNames = { "Name", "Publisher", "Details"};
		String[][] list = new String[dataList.size()][3];
		for (int i = 0; i < dataList.size(); i++) {
			list[i][0] = dataList.get(i).getName();
			list[i][2] = "Details";
		}
		resultTable = new JTable(list, columnNames);
		resultTable.setEnabled(false);
		resultTable.setBounds(0, 50, 1000, 600);
		TableColumnModel columnModel = resultTable.getColumnModel();
		columnModel.getColumn(0).setWidth(300);
		columnModel.getColumn(1).setWidth(75);
		columnModel.getColumn(2).setWidth(250);
		//columnModel.getColumn(3).setWidth(150);

		add(new JScrollPane(resultTable));
		resultTable.setFillsViewportHeight(true);
		JScrollBar scrollBar = new JScrollBar();
		resultTable.add(scrollBar);
		add(resultTable, BorderLayout.CENTER);
	}

	/**
	 * Constructeur de la classe
	 */
	public CharacterSearchPanel() {
		setBounds(150, 0, 1000, 600);
		textField = new JTextField();
		add(textField); // On ajoute la zone de recherche au panel
		textField.setColumns(10);
		search = new JButton("Search");
		add(search); // On ajoute le bouton au panel
		CharacterSearchListener csl = new CharacterSearchListener(this);
		search.addMouseListener(csl);
	}

}
