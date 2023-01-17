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

public class DetailsButtonLikeListener extends MouseAdapter{
	DetailsComic detailspanel = null;
	
	@SuppressWarnings("unused")
	private DetailsButtonLikeListener() {
	}
	
	public DetailsButtonLikeListener(DetailsComic details) {
		super();
		this.detailspanel = details;
	}
	
	public void mouseClicked(MouseEvent e) {
		if ((((JButton) e.getSource()).getText()).equals("Like")) {
			System.out.println("like");
			like();
		} else {
			if ((((JButton) e.getSource()).getText()).equals("Liked")) {
				System.out.println("dislike");
				disLike();
		}
		}
	}
	
	public void like() {
		Connection connection = null;
		try {
			// Chargement du pilote SQLite
			Class.forName("org.sqlite.JDBC");

			// Connexion à la base de données
			connection = DriverManager.getConnection("jdbc:sqlite:Account.db");

			// Création d'une requête
			String query = "INSERT INTO Account_comic_like VALUES ('"+ User.getLogin() + "','" + detailspanel.getIssue() + "')";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			detailspanel.setLikeBtn("Liked");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
	public void disLike() {
		Connection connection = null;
		try {
			// Chargement du pilote SQLite
			Class.forName("org.sqlite.JDBC");

			// Connexion à la base de données
			connection = DriverManager.getConnection("jdbc:sqlite:Account.db");

			// Création d'une requête
			String query = "DELETE FROM Account_comic_like WHERE Login = '" + User.getLogin() + "' AND comicId = "+detailspanel.getIssue();
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			detailspanel.setLikeBtn("Like");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
