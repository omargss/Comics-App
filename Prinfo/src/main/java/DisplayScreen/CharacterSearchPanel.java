package DisplayScreen;

import Objects.Comic;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.util.List;

/**
 * Classe permettant de créer le panel permettant de faire des recherches de
 * personnages
 *
 */
public class CharacterSearchPanel extends JPanel {

	private JTable resultTable = new JTable();
	private JTextField textField;
	private JButton search;

	/**
	 * 
	 * @return JTextField textField
	 */
	public JTextField getTextField() {
		return textField;
	}

	// METHOD that changes the results of the JTable
	/**
	 * 
	 * @param dataList
	 */
	public void updateResultTable(List<Comic> dataList) {
		String[] columnNames = { "Title", "date", "Publisher", "Volume", "Access page" };
		String[][] list = new String[dataList.size()][5];
		for (int i = 0; i < dataList.size(); i++) {
			list[i][0] = dataList.get(i).getName();
			list[i][1] = dataList.get(i).getDate();
			list[i][2] = dataList.get(i).getVolume();
			list[i][3] = dataList.get(i).getPublisher();
			list[i][4] = "Details";
		}
		resultTable = new JTable(list, columnNames);
		resultTable.setEnabled(false);
		resultTable.setBounds(0, 50, 1000, 600);
		TableColumnModel columnModel = resultTable.getColumnModel();
		columnModel.getColumn(0).setWidth(300);
		columnModel.getColumn(1).setWidth(75);
		columnModel.getColumn(2).setWidth(250);
		columnModel.getColumn(3).setWidth(150);

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
	}

}
