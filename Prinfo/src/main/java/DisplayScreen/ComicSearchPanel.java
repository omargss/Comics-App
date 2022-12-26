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

/**
 * Classe permettant de créer le panel qui permettra de réaliser des recherches
 * de comics
 *
 */
public class ComicSearchPanel extends JPanel {

	private JTable resultTable = new JTable();
	private JTextField textField;
	private JButton search;
	private JLabel sortByLabel = new JLabel("Sort by :");
	private JComboBox dropDownSortFieldChoice;
	private JComboBox dropDownSortOrder;
	private JComboBox dropDownYearsMin;
	private JComboBox dropDownYearsMax;

	/**
	 * 
	 * @return JTextField textField
	 */
	public JTextField getTextField() {
		return textField;
	}

	/**
	 * Permet de savoir sur quel critère on effectue un tri
	 * 
	 * @return String paramètrant le champ de tri
	 */
	public String getDropDownSortFieldChoice() {
		String choice;
		switch (dropDownSortFieldChoice.getSelectedIndex()) {
		case 1:
			choice = "name";
			break;

		case 2:
			choice = "date";
			break;

		default:
			choice = "null";
			break;
		}
		return choice;
	}

	/**
	 * Permet de savoir de quelle manière on effectue le tri (croissant/décroissant)
	 * 
	 * @return String paramètrant l'ordre du tri
	 */
	public String getDropDownSortOrder() {
		String choice;
		switch (dropDownSortOrder.getSelectedIndex()) {
		case 1:
			choice = "asc";
			break;

		case 2:
			choice = "desc";
			break;

		default:
			choice = "null";
			break;
		}
		return choice;
	}

	/**
	 * Permet de connaitre l'année minimale pour la recherche
	 * 
	 * @return String donnant une année
	 */
	public String getDropDownYearsMin() {
		return dropDownYearsMin.getSelectedItem().toString();
	}

	/**
	 * Permet de connaitre l'année maximale pour la recherche
	 * 
	 * @return String donnant une année
	 */
	public String getDropDownYearsMax() {
		return dropDownYearsMax.getSelectedItem().toString();
	}

	JRadioButton radioTitle = new JRadioButton("Title", true); // Bouton radio pour la recherche par titre, par défaut
																// activé en premier pour la recherche
	JRadioButton radioAuthor = new JRadioButton("Author"); // Bouton radio pour la recherche par auteur
	JRadioButton radioPublisher = new JRadioButton("Publisher"); // Bouton radio pour la recherche par publieur
	ButtonGroup radioGroup = new ButtonGroup(); // Groupement de boutons radio pour en avoir un seul sélectionné

	/**
	 * Permet de savoir quel bouton radio est sélectionné
	 * 
	 * @return String : label du bouton radio sélectionné
	 */
	public String getRadioValue() {
		return radioGroup.getSelection().getActionCommand();
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
	public ComicSearchPanel() {
		setBounds(150, 0, 1000, 600);

		textField = new JTextField();
		add(textField); // On ajoute la zone de recherche au panel
		textField.setColumns(10);

		search = new JButton("Search");
		add(search); // On ajoute le bouton au panel

		radioTitle.setActionCommand("Title");
		radioPublisher.setActionCommand("Publisher");
		radioAuthor.setActionCommand("Author");
		// On ajoute les boutons radio au groupement afin qu'un seul soit sélectionné à la fois
		radioGroup.add(radioAuthor);
		radioGroup.add(radioPublisher);
		radioGroup.add(radioTitle);
		// On ajoute les boutons radios au panel
		add(radioTitle);
		add(radioPublisher);
		//add(radioAuthor);

		// On crée la liste des années entre 1900 et aujourd'hui
		int currentYear = Year.now().getValue();
		ArrayList<String> years = new ArrayList<String>();
		String yearStr = "1900";
		for (Integer i = 1900; i <= currentYear; i++) {
			yearStr = i.toString();
			years.add(yearStr);
		}

		// Declaring Array with Equal Size to the List
		String[] yearsArray = new String[years.size()];

		// Converting List to Array
		years.toArray(yearsArray);
		dropDownYearsMin = new JComboBox(yearsArray);
		dropDownYearsMax = new JComboBox(yearsArray);
		// année max selectionnée par défaut : l'année actuelle
		dropDownYearsMax.setSelectedItem(yearStr);
		add(dropDownYearsMin); // Ajout du bouton au panel
		add(dropDownYearsMax); // Ajout du bouton au panel
		add(sortByLabel); // Ajout du bouton au panel

		dropDownSortFieldChoice = new JComboBox();
		dropDownSortFieldChoice.addItem("");
		dropDownSortFieldChoice.addItem("Name");
		dropDownSortFieldChoice.addItem("Date");
		add(dropDownSortFieldChoice); // Ajout du bouton au panel
		dropDownSortOrder = new JComboBox();
		dropDownSortOrder.addItem("");
		dropDownSortOrder.addItem("Ascending order");
		dropDownSortOrder.addItem("Descending order");
		add(dropDownSortOrder); // Ajout du bouton au panel

		// Listeners
		ComicSearchListener csl = new ComicSearchListener(this);
		search.addMouseListener(csl);
		ComicSearchKeyListener cskl = new ComicSearchKeyListener(this);
		textField.addKeyListener(cskl);
	}

}
