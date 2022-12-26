package DisplayScreen;

import Listeners.ComicSearchKeyListener;
import Listeners.ComicSearchListener;
import Objects.Comic;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
	private JScrollPane resultArea = new JScrollPane();
	private JPanel searchArea = new JPanel();
	private JComboBox<String> dropDownSortFieldChoice;
	private JComboBox<String> dropDownSortOrder;
	private JComboBox<Object> dropDownYearsMin;
	private JComboBox<Object> dropDownYearsMax;
	private JComboBox<String> dropItems;

	private JRadioButton radioTitle = new JRadioButton("Title", true); // Bouton radio pour la recherche par titre, par
																		// défaut activé en premier pour la recherche
	private JRadioButton radioAuthor = new JRadioButton("Author"); // Bouton radio pour la recherche par auteur
	private JRadioButton radioPublisher = new JRadioButton("Publisher"); // Bouton radio pour la recherche par publieur
	private ButtonGroup radioGroup = new ButtonGroup(); // Groupement de boutons radio pour en avoir un seul sélectionné

	/**
	 * Permet de retourner le contenu du champ de saisie pour les recherches
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

	/**
	 * Permet de savoir quel bouton radio est sélectionné
	 * 
	 * @return String : label du bouton radio sélectionné
	 */
	public String getRadioValue() {
		return radioGroup.getSelection().getActionCommand();
	}

	/**
	 * Permet de connaitre le nombre de résultats à afficher
	 * 
	 * @return String : nombre de résultats à afficher
	 */
	public String getDropItem() {
		return (this.dropItems.getSelectedItem().toString());
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

		resultTable.setFillsViewportHeight(true);

		this.remove(resultArea);
		this.resultArea = new JScrollPane(resultTable);
		this.resultArea.setVisible(true);

		this.add(resultArea, BorderLayout.CENTER);
		this.validate();

		// add(new JScrollPane(resultTable));
		//
		// JScrollBar scrollBar = new JScrollBar();
		// resultTable.add(scrollBar);
		// add(resultTable, BorderLayout.CENTER);
	}

	/**
	 * Constructeur de la classe
	 */
	public ComicSearchPanel() {
		this.resultArea.setVisible(false);
		this.setLayout(new BorderLayout());
		setBounds(150, 0, 1000, 600);

		// Zone de recherche
		textField = new JTextField();
		textField.setColumns(10);

		// Bouton pour valider la recherche
		search = new JButton("Search by :");

		// Boutons radios pour valider le type de recherche
		radioTitle.setActionCommand("Title");
		radioPublisher.setActionCommand("Publisher");
		radioAuthor.setActionCommand("Author");
		// On ajoute les boutons radio au groupement afin qu'un seul soit sélectionné à
		// la fois
		radioGroup.add(radioAuthor);
		radioGroup.add(radioPublisher);
		radioGroup.add(radioTitle);
		// On ajoute les boutons radios au panel

		// add(radioAuthor);

		// Liste des années
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
		dropDownYearsMin = new JComboBox<Object>(yearsArray);
		dropDownYearsMax = new JComboBox<Object>(yearsArray);
		// année max selectionnée par défaut : l'année actuelle
		dropDownYearsMax.setSelectedItem(yearStr);

		// Sélecteur de l'option de tri
		dropDownSortFieldChoice = new JComboBox<String>();
		dropDownSortFieldChoice.addItem(""); // Ajout de l'option vide
		dropDownSortFieldChoice.addItem("Name"); // Ajout de l'option "par nom"
		dropDownSortFieldChoice.addItem("Date"); // Ajout de l'option "par date"

		// Sélecteur de l'ordre de tri
		dropDownSortOrder = new JComboBox<String>();
		dropDownSortOrder.addItem(""); // Ajout de l'option vide
		dropDownSortOrder.addItem("Ascending order"); // Ajout de l'option "croissant"
		dropDownSortOrder.addItem("Descending order"); // Ajout de l'option "décroissant"

		// Sélecteur du nombre de résultat à afficher
		dropItems = new JComboBox<String>();
		dropItems.addItem("All results"); // Ajout de l'option vide
		dropItems.addItem("5 items"); // Ajout de l'option "5 résultats"
		dropItems.addItem("10 items"); // Ajout de l'option "10 résultats"
		dropItems.addItem("25 items"); // Ajout de l'option "25 résultats"
		dropItems.addItem("50 items"); // Ajout de l'option "50 résultats"
		dropItems.addItem("100 items"); // Ajout de l'option "100 résultats"
		dropItems.setSelectedIndex(3); // Par défaut, on affichera que 25 items

		// Zone d'ajout au panel de recherche
		JPanel firstArea = new JPanel();
		firstArea.add(textField); // On ajoute la zone de recherche au panel
		firstArea.add(search); // On ajoute le bouton au panel
		firstArea.add(radioTitle);
		firstArea.add(radioPublisher);
		JPanel secondArea = new JPanel();
		secondArea.add(new JLabel("From"));
		secondArea.add(dropDownYearsMin); // Ajout du bouton au panel
		secondArea.add(new JLabel("to"));
		secondArea.add(dropDownYearsMax); // Ajout du bouton au panel
		secondArea.add(sortByLabel); // Ajout du label au panel
		secondArea.add(dropDownSortFieldChoice); // Ajout du sélecteur au panel
		secondArea.add(dropDownSortOrder); // Ajout du sélecteur au panel
		secondArea.add(dropItems); // Ajout du sélecteur au panel
		this.searchArea.setLayout(new BorderLayout());
		this.searchArea.add(firstArea, BorderLayout.NORTH);
		this.searchArea.add(secondArea, BorderLayout.SOUTH);
		this.add(searchArea, BorderLayout.NORTH);

		// Listeners
		ComicSearchListener csl = new ComicSearchListener(this);
		search.addMouseListener(csl);
		ComicSearchKeyListener cskl = new ComicSearchKeyListener(this);
		textField.addKeyListener(cskl);
	}

}
