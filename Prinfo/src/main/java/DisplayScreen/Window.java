package DisplayScreen;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.Year;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.*;

import GetData.GetComicsData;
import Listeners.Keyboard;
import Listeners.Mouse;
import Objects.Comic;

public class Window{
	JFrame window=null;
	int width=1000;
	int height=600;
	
	// Objets intéractifs définis dans le constructeur pour les utiliser dans des méthodes externes
	JRadioButton radioTitle = new JRadioButton("Title",true);
	JRadioButton radioPublisher = new JRadioButton("Publisher");
	JTextField entry = new JTextField("Recherche");
	JButton search = new JButton("Search");
	JTextArea APIReturn = new JTextArea();
	ButtonGroup radioGroup = new ButtonGroup();

	public String getDropDownYearsMin() {
		return dropDownYearsMin.getSelectedItem().toString();
	}

	public String getDropDownYearsMax() {
		return dropDownYearsMax.getSelectedItem().toString();
	}

	private JComboBox dropDownYearsMin;
	private JComboBox dropDownYearsMax;
	private JTable table;
	JPanel searches = new JPanel();
	JPanel results = new JPanel();
	private final JComboBox filter_comboBox = new JComboBox();
	// Méthodes
		// Méthodes liées au bouton "rechercher"
	public JButton get_button() {
		return(search);
	}
	
		// Méthodes liées aux boutons radios
	public String get_radio_value() { // Permet de savoir quel bouton radio est sélectionné
		for(Enumeration<AbstractButton> iterator=radioGroup.getElements();iterator.hasMoreElements();) {
			AbstractButton test_bouton=iterator.nextElement();
			//System.out.println(test_bouton.isSelected());
			if(test_bouton.isSelected()) {
				return(test_bouton.getText());
			}
		}
		return("");
	}
	
		// Méthodes liées à la combo box de filtrage (ordre croissant décroissant)
	public String get_combobox_value() {
		return filter_comboBox.getSelectedItem().toString();
	}
	
		// Méthodes liées à la zone de saisie "titre"
	public JTextField get_area() {
		return(entry);
	}
	public String get_text() {
		return(entry.getText());
	}
	public void set_text(String text) {
		entry.setText(text);
	}
	
		// Méthodes liées à la zone d'affichage des résultats
	public void set_results(String results) {
		APIReturn.setText(results);
	};
	
	public void set_results(List<Comic> dataList) {
		String[] columnNames = {"Title", "date","Publisher", "Volume","Access page"};
		String[][] list = new String[dataList.size()][5];
		for(int i = 0; i< dataList.size(); i++) {
			list[i][0] = dataList.get(i).getName();
			list[i][1] = dataList.get(i).getDate();
			list[i][2] = dataList.get(i).getVolume();
			list[i][3] = dataList.get(i).getPublisher();
			list[i][4] = "Details";
		}
		//APIReturn = new JTextArea();
		//APIReturn.setEditable(false);
		APIReturn.removeAll();

		table = new JTable(list,columnNames);
		table.setEnabled(false);
		table.setBounds(0, 60, 800, 240);;
		results.add(new JScrollPane(table));
		table.setFillsViewportHeight(true);
		JScrollBar scrollBar = new JScrollBar();
		table.add(scrollBar);
		APIReturn.add(table,BorderLayout.CENTER);
		window.pack();

	}
	
	public Window() {
		// Définition de la fenetre d'affichage
		window=new JFrame(); // permet de définir la var comme nouvelle Frame
		JPanel contentPane = (JPanel) window.getContentPane(); // Définition de la variable ContentPane en raccourci
		window.setVisible(true); // permet de savoir si elle est visible ou non
		window.setPreferredSize(new Dimension(width,height)); // dimension de la fenetre
		window.setSize(new Dimension(width,height)); // dimension de la fenetre
		window.setLocationRelativeTo(null); // permet de centrer par rapport à un component, si component = null, centre par rapport au bureau
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer le processus quand on quitte la fenetre java

		// Panels
		results.setVisible(true);
		results.setSize(new Dimension(width,height-300));
		
		// Listeners
		Mouse mouse = new Mouse(this);
		Keyboard keyboard = new Keyboard(this);
		
		// Objets intéractifs
			// Groupement des boutons radios
		radioGroup.add(radioPublisher);
		radioGroup.add(radioTitle);
			// Zone de saisie pour la recherche
		entry.setPreferredSize(new Dimension(200,30));
		entry.addMouseListener(mouse);
		entry.addKeyListener(keyboard);
			// Bouton de validation de la recherche
		search.addMouseListener(mouse);
			// Affichage des résultats
		APIReturn.setEditable(false);
		JScrollPane scroll = new JScrollPane (APIReturn); // Permet de faire défiler l'écran pour voir le reste des résultats
		//results.add(scroll);
		
		// Ajout dans le panel de la recherche
		searches.add(entry);
		searches.add(radioTitle);
		searches.add(radioPublisher);
		filter_comboBox.setModel(new DefaultComboBoxModel(new String[] {"nom croissant", "nom decroissant", "date croissante", "date decroissante", "publisher croissant", "publisher decroissant"}));

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

		searches.add(filter_comboBox);
		searches.add(dropDownYearsMin);
		searches.add(dropDownYearsMax);
		searches.add(search);




		// Ajout dans le panel principal
		contentPane.add(searches,BorderLayout.NORTH);
		contentPane.add(scroll,BorderLayout.CENTER);
		window.pack();
	}
}