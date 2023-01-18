package Listeners;

import DisplayScreen.*;
import Security.PasswordEncryption;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterButtonListener extends MouseAdapter {

	static RegisterPanel rgpanel = null;

	/**
	 * Constructeur de la classe
	 * 
	 */
	public RegisterButtonListener(RegisterPanel rgp) {
		super();
		RegisterButtonListener.rgpanel = rgp;
	}

	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource(); // On récupère l'objet sur lequel l'utilisateur a cliqué
		if (o instanceof JButton) { // S'il s'agit d'un JButton alors on peut continuer
			JButton b = (JButton) o;
			if (b.getText().equals("Register")) {
				register();
			}

		}

	}

	public static void register() {
		Connection connection = null;
		try {
			// Chargement du pilote SQLite
			Class.forName("org.sqlite.JDBC");

			// Connexion à la base de données
			connection = DriverManager.getConnection("jdbc:sqlite:Account.db");

			// Création d'une requête
			String query = "INSERT INTO Accounts VALUES ('" + rgpanel.getLogin() + "','" + PasswordEncryption.hashPassword(rgpanel.getPassword())
					+ "','0')";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			rgpanel.setConnectionSuccessful();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			rgpanel.setUserAlreadyExists();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			rgpanel.setConnectionFailed();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				rgpanel.setConnectionFailed();
			}
		}
	}
}