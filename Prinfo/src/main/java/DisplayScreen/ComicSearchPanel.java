package DisplayScreen;

import Listeners.ComicSearchKeyListener;
import Listeners.ComicSearchListener;
import Objects.Comic;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ComicSearchPanel extends JPanel {
	public JTextField getTextField() {
		return textField;
	}
	private JTable resultTable = new JTable();
	private JTextField textField;
	private JButton search;

	private JComboBox dropDownYearsMin;
	private JComboBox dropDownYearsMax;
	public String getDropDownYearsMin() {
		return dropDownYearsMin.getSelectedItem().toString();
	}

	public String getDropDownYearsMax() {
		return dropDownYearsMax.getSelectedItem().toString();
	}

	JRadioButton radioTitle = new JRadioButton("Title",true);
	JRadioButton radioPublisher = new JRadioButton("Publisher");
	ButtonGroup radioGroup = new ButtonGroup();

	public String getRadioValue() {
		return radioGroup.getSelection().getActionCommand();
	}
	// METHOD that changes the results of the JTable
	public void updateResultTable(List<Comic> dataList) {
		String[] columnNames = {"Title", "date","Publisher", "Volume","Access page"};
		String[][] list = new String[dataList.size()][5];
		for(int i = 0; i< dataList.size(); i++) {
			list[i][0] = dataList.get(i).getName();
			list[i][1] = dataList.get(i).getDate();
			list[i][2] = dataList.get(i).getVolume();
			list[i][3] = dataList.get(i).getPublisher();
			list[i][4] = "Details";
		}
		resultTable = new JTable(list,columnNames);
		resultTable.setEnabled(false);
		resultTable.setBounds(0,50, 1000, 600);
		TableColumnModel columnModel = resultTable.getColumnModel();
		columnModel.getColumn(0).setWidth(300);
		columnModel.getColumn(1).setWidth(75);
		columnModel.getColumn(2).setWidth(250);
		columnModel.getColumn(3).setWidth(150);

		add(new JScrollPane(resultTable));
		resultTable.setFillsViewportHeight(true);
		JScrollBar scrollBar = new JScrollBar();
		resultTable.add(scrollBar);
		add(resultTable,BorderLayout.CENTER);
	}

	public ComicSearchPanel() {
		setBounds(150,0,1000,600);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);

		search = new JButton("Search");
		add(search);


		radioTitle.setActionCommand("Title");
		radioPublisher.setActionCommand("Publisher");
		radioGroup.add(radioPublisher);
		radioGroup.add(radioTitle);

		add(radioTitle);
		add(radioPublisher);

		int currentYear = Year.now().getValue();
		ArrayList<String> years = new ArrayList<String>();
		String yearStr="1900";
		for(Integer i=1900;i<=currentYear;i++)
		{
			yearStr = i.toString();
			years.add(yearStr);
		}

		//Declaring Array with Equal Size to the List
		String[] yearsArray = new String [years.size()];

		//Converting List to Array
		years.toArray(yearsArray);
		dropDownYearsMin = new JComboBox(yearsArray);
		dropDownYearsMax = new JComboBox(yearsArray);
		// année max selectionnée par défaut : l'année actuelle
		dropDownYearsMax.setSelectedItem(yearStr);
		add(dropDownYearsMin);
		add(dropDownYearsMax);

		// Listeners
		ComicSearchListener csl = new ComicSearchListener(this);
		search.addMouseListener(csl);
		ComicSearchKeyListener cskl = new ComicSearchKeyListener(this);
		textField.addKeyListener(cskl);
	}

}

