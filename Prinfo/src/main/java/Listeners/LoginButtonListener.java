package Listeners;

import DisplayScreen.*;
import Objects.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginButtonListener extends MouseAdapter {

	static LoginPanel lgpanel = null;

	/**
	 * Constructeur de la classe
	 * 
	 */
	public LoginButtonListener(LoginPanel lgp) {
		super();
		this.lgpanel = lgp;
	}

	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource(); // On récupère l'objet sur lequel l'utilisateur a cliqué
		if (o instanceof JButton) { // S'il s'agit d'un JButton alors on peut continuer
			JButton b = (JButton) o;
			if (b.getText().equals("Login")) {
				checkLogin();
			}

		}

	}

	public static boolean checkLogin() {
		Connection connection = null;
		try {
			// Chargement du pilote SQLite
			Class.forName("org.sqlite.JDBC");

			// Connexion à la base de données
			connection = DriverManager.getConnection("jdbc:sqlite:Account.db");

			// Création d'une requête
			String query = "SELECT * FROM Accounts WHERE Login = '" + lgpanel.getLogin() + "' AND Password = '"
					+ lgpanel.getPassword() + "'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// Vérifier si l'utilisateur a été trouvé
			if (resultSet.next()) {
				// voir comment faire pour mettre un paramètre global de login
				User.setLogin(lgpanel.getLogin());
				lgpanel.setConnectionSuccessful();
				return true;
			} else {
				lgpanel.setConnectionFailed();
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
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