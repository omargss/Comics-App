package DisplayScreen;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

import Listeners.PublisherSearchKeyListener;
import Listeners.PublisherSearchListener;
import Listeners.TableListener;
import Objects.Publisher;

public class PublisherSearchPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JButton search;
	private JTable resultTable = new JTable();
	private JScrollPane resultArea = new JScrollPane(); // Zone de résultat
	private List<Publisher> dataList;

	/**
	 * Permet de retourner le contenu du champ de saisie pour les recherches
	 * 
	 * @return JTextField textField
	 */
	public JTextField getTextField() {
		return this.textField;
	}

	public List<Publisher> getDataList() {
		return dataList;
	}

	public void updateResultTable(List<Publisher> dataList) {
		this.dataList = dataList;
		String[] columnNames = { "Name", "Details" }; // Permet de nommer les colonnes
		String[][] list = new String[dataList.size()][2]; // Création de la matrice de résultats pour afficher les infos
		for (int i = 0; i < dataList.size(); i++) {
			list[i][0] = dataList.get(i).getName(); // Attribution du nom
			list[i][1] = "Details"; // Permet par la suite d'ouvrir une nouvelle fenetre affichant les détails du
									// personnage
		}
		resultTable = new JTable(list, columnNames); // Permet la mise en forme sous forme de tableau
		resultTable.setEnabled(false); // Enlève la possibilité à l'utilisateur de la modifier
		resultTable.addMouseListener(new TableListener(this));
		resultTable.setBounds(0, 50, 1000, 600);
		TableColumnModel columnModel = resultTable.getColumnModel(); // Permet de définir les colonnes
		columnModel.getColumn(0).setWidth(300);
		columnModel.getColumn(1).setWidth(300);

		this.remove(resultArea); // On enlève ce qu'il y avait avant pour éviter les surcouches
		this.resultArea = new JScrollPane(resultTable); // On crée un nouveau panel affichant la table des résultats
		this.resultArea.setVisible(true); // On affiche le panel

		this.add(this.resultArea, BorderLayout.CENTER); // On ajoute le panel au panel principal
		this.validate(); // On valide les modifications
	}

	/**
	 * Constructeur de la classe
	 */
	public PublisherSearchPanel() {
		this.resultArea.setVisible(false);
		this.setLayout(new BorderLayout()); // Permet de définir le mode de disposition des components
		setBounds(150, 0, 1000, 600);
		JPanel searchArea = new JPanel(); // Zone de recherche

		// Création des componenets de la zone de recherche
		textField = new JTextField(); // Zone de saisie
		// add(textField); // On ajoute la zone de recherche au panel
		textField.setColumns(10);
		search = new JButton("Search"); // Bouton pour valider la recherche
		// add(search); // On ajoute le bouton au panel

		// Ajout des components à la zone de recherche
		searchArea.add(textField);
		searchArea.add(search);

		// Ajout des panels au panel principal
		this.add(searchArea, BorderLayout.NORTH);
		this.add(resultArea, BorderLayout.CENTER);

		// Listeners
		PublisherSearchListener psl = new PublisherSearchListener(this); // Listener lors d'un clic
		this.search.addMouseListener(psl); // Lorsqu'on clique sur le bouton pour rechercher
		PublisherSearchKeyListener pskl = new PublisherSearchKeyListener(this); // Listener lorsqu'on appuie sur entrée
		this.textField.addKeyListener(pskl); // Lorsqu'on appuie sur entrée dans la barre de saisie
	}

	/**
	 * Permet de récupérer la table des résultats
	 * 
	 * @return JTable
	 */
	public JTable getResultTable() {
		return resultTable;
	}

}
