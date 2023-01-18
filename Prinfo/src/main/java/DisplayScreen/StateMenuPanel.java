package DisplayScreen;

import GetData.GetComicsData;
import Listeners.StateMenuChoiceListener;
import Listeners.TableListener;
import Objects.Comic;
import Objects.User;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de créer le panel qui permettra de réaliser des recherches
 * de comics
 *
 */
public class StateMenuPanel extends JPanel {

	private JTable resultTable = new JTable();
	private JScrollPane resultArea = new JScrollPane();
	private List<Comic> dataList;
	private JRadioButton rdbtnRead;
	private JRadioButton rdbtnInProgress;
	private JRadioButton rdbtnWantToRead;

	public void updateResultTable(String filter) {
		dataList = new ArrayList<Comic>();
		dataList = getReadComics(filter);
		String[] columnNames = { "Title", "date", "Publisher", "Volume", "Access page" };
		String[][] list = new String[dataList.size()][5];
		for (int i = 0; i < dataList.size(); i++) {
			list[i][0] = dataList.get(i).getName();
			list[i][1] = dataList.get(i).getDate();
			list[i][2] = dataList.get(i).getPublisher();
			list[i][3] = dataList.get(i).getVolume();
			list[i][4] = "Details";
		}
		resultTable = new JTable(list, columnNames);
		resultTable.setEnabled(false);
		resultTable.addMouseListener(new TableListener(this));
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

	private List<Comic> getReadComics(String filter) {
		List<Comic> datalist = new ArrayList<Comic>();
		List<Long> comicIdList = new ArrayList<Long>();
		comicIdList = getIssues(filter);
		datalist = GetComicsData.getComicsDataByID(comicIdList);
		return datalist;
	}

	public JTable getResultTable() {
		return resultTable;
	}

	public void setResultTable(JTable resultTable) {
		this.resultTable = resultTable;
	}

	/**
	 * Constructeur de la classe
	 */
	public StateMenuPanel() {
		this.resultArea.setVisible(false);
		this.setLayout(new BorderLayout());
		setBounds(150, 0, 1000, 600);

		JPanel selectArea = new JPanel();
		add(selectArea, BorderLayout.NORTH);

		rdbtnRead = new JRadioButton("Read");
		rdbtnRead.setSelected(true);
		selectArea.add(rdbtnRead);

		rdbtnInProgress = new JRadioButton("In progress");
		selectArea.add(rdbtnInProgress);

		rdbtnWantToRead = new JRadioButton("Want to read");
		selectArea.add(rdbtnWantToRead);

		// JButton readButton = new JButton("Read");
		// selectArea.add(readButton);

		// JButton InProgressButton = new JButton("In progress");
		// selectArea.add(InProgressButton);

		// JButton WantToReadButton = new JButton("Want to read");
		// selectArea.add(WantToReadButton);

		StateMenuChoiceListener listener = new StateMenuChoiceListener(this, rdbtnRead, rdbtnInProgress,
				rdbtnWantToRead);
		// readButton.addMouseListener(listener);
		// InProgressButton.addMouseListener(listener);
		// WantToReadButton.addMouseListener(listener);
		rdbtnRead.addMouseListener(listener);
		rdbtnInProgress.addMouseListener(listener);
		rdbtnWantToRead.addMouseListener(listener);
	}

	public static List<Long> getIssues(String filter) {
		Connection connection = null;
		try {
			// Chargement du pilote SQLite
			Class.forName("org.sqlite.JDBC");

			// Connexion à la base de données
			connection = DriverManager.getConnection("jdbc:sqlite:Account.db");

			// Création d'une requête
			String query = "SELECT * FROM account_comic_state WHERE Login = '" + User.getLogin() + "' AND State = '"
					+ filter + "'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			List<Long> ComicID = new ArrayList<Long>();

			// Vérifier si l'utilisateur a été trouvé
			while (resultSet.next()) {
				ComicID.add(resultSet.getLong("ComicId"));
			}
			return ComicID;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public List<Comic> getDataList() {
		return dataList;
	}
	
	public void setbtnReadSelected() {
		rdbtnRead.setSelected(true);
		rdbtnInProgress.setSelected(false);
		rdbtnWantToRead.setSelected(false);
	}
	
	public void setbtnInProgressSelected() {
		rdbtnRead.setSelected(true);
		rdbtnRead.setSelected(false);
		rdbtnWantToRead.setSelected(false);
	}
	
	public void setbtnWantToReadSelected() {
		rdbtnRead.setSelected(true);
		rdbtnInProgress.setSelected(false);
		rdbtnRead.setSelected(false);
	}
}
