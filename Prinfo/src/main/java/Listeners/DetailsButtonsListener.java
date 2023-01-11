package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;

import DisplayScreen.Details;
import DisplayScreen.MainWindow;
import Objects.User;

public class DetailsButtonsListener extends MouseAdapter{
	Details detailspanel = null;
	
	@SuppressWarnings("unused")
	private DetailsButtonsListener() {
	}
	
	public DetailsButtonsListener(Details details) {
		super();
		this.detailspanel = details;
	}
	
	public void mouseClicked(MouseEvent e) {
		if ((((JButton) e.getSource()).getText()).equals("Lu")) {
			insertComic("lu");
		}
		
		if ((((JButton) e.getSource()).getText()).equals("Envie de lire")) {
			insertComic("envie de lire");
		}
		
		if ((((JButton) e.getSource()).getText()).equals("En cours de lecture")) {
			insertComic("en cours de lecture");
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
			String query = "INSERT INTO Account_comic_state VALUES ('"+ User.getLogin() + "','" + detailspanel.getIssue() + "','" +etat+"')";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			if(e.getErrorCode() == 19) {
				try {
					String query = "UPDATE Account_comic_state SET State ='"+ etat + "'WHERE Login = '"+ User.getLogin()+"' and comicId = "+ detailspanel.getIssue();
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
