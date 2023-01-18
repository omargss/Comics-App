package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;

import DisplayScreen.DetailsComic;
import Objects.User;
import DisplayScreen.MainWindow;

public class DetailsButtonsListener extends MouseAdapter {
	DetailsComic detailspanel = null;

	@SuppressWarnings("unused")
	private DetailsButtonsListener() {
	}

	public DetailsButtonsListener(DetailsComic details) {
		super();
		this.detailspanel = details;
	}

	public void mouseClicked(MouseEvent e) {
		if ((((JButton) e.getSource()).getText()).equals("Readed")) {
			removeComic("Read");
			detailspanel.setbtnRead("Add : Read");
		} else if ((((JButton) e.getSource()).getText()).equals("Add : Read")) {
			insertComic("Read");
			detailspanel.setbtnRead("Readed");
			detailspanel.setbtnWantToRead("Add : Want to read");
			detailspanel.setbtnInProgress("Add : In progress");
		}

		if ((((JButton) e.getSource()).getText()).equals("Want to read")) {
			removeComic("Want to read");
			detailspanel.setbtnWantToRead("Add : Want to read");
		} else if ((((JButton) e.getSource()).getText()).equals("Add : Want to read")) {
			insertComic("Want to read");
			detailspanel.setbtnWantToRead("Want to read");
			detailspanel.setbtnRead("Add : Read");
			detailspanel.setbtnInProgress("Add : In progress");
		}

		if ((((JButton) e.getSource()).getText()).equals("In progress")) {
			removeComic("In progress");
			detailspanel.setbtnInProgress("Add : In progress");
		} else if ((((JButton) e.getSource()).getText()).equals("Add : In progress")) {
			insertComic("In progress");
			detailspanel.setbtnInProgress("In progress");
			detailspanel.setbtnRead("Add : Read");
			detailspanel.setbtnWantToRead("Add : Want to read");
		}
	}

	public void insertComic(String etat) {
		Connection connection = null;
		try {
			// Chargement du pilote SQLite
			Class.forName("org.sqlite.JDBC");

			// Connexion à la base de données
			connection = DriverManager.getConnection("jdbc:sqlite:Account.db");

			// Création d'une requête
			String query = "INSERT INTO Account_comic_state VALUES ('" + User.getLogin() + "','"
					+ detailspanel.getIssue() + "','" + etat + "')";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			if (e.getErrorCode() == 19) {
				try {
					String query = "UPDATE Account_comic_state SET State ='" + etat + "'WHERE Login = '"
							+ User.getLogin() + "' and comicId = " + detailspanel.getIssue();
					Statement statement = connection.createStatement();
					statement.executeUpdate(query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
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

	public void removeComic(String etat) {
		Connection connection = null;
		try {
			// Chargement du pilote SQLite
			Class.forName("org.sqlite.JDBC");

			// Connexion à la base de données
			connection = DriverManager.getConnection("jdbc:sqlite:Account.db");

			// Création d'une requête
			String query = "DELETE FROM Account_comic_state WHERE Login = '" + User.getLogin() + "' AND comicId = "
					+ detailspanel.getIssue();
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			if (e.getErrorCode() == 19) {
				try {
					String query = "UPDATE Account_comic_state SET State ='" + etat + "'WHERE Login = '"
							+ User.getLogin() + "' and comicId = " + detailspanel.getIssue();
					Statement statement = connection.createStatement();
					statement.executeUpdate(query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
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
}
