package DisplayScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Listeners.DetailsButtonLikeListener;
import Listeners.DetailsButtonsListener;
import Listeners.FollowingListener;
import Objects.Comic;
import Objects.User;

public class DetailsComic extends JFrame {
	long issue;
	String name;
	String imageURL;
	Image image;
	String date;
	String publisher;
	String description = "null à plus tard";
	Comic comic;
	JButton btnLike;
	
	public DetailsComic(Comic comic) {
		System.out.println("premium "+User.isPremium());
		/*
		 * Contenu d'un comic : - volume
		 */

		// Définition de la fenetre à afficher
		JFrame frame = new JFrame();
		BorderLayout frameBorder = new BorderLayout();
		frameBorder.setHgap(10);
		frame.getContentPane().setLayout(frameBorder);
		frame.setVisible(true);

		// Définition des panels pour afficher les infos
		// JPanel window = new JPanel(new BorderLayout());

		// Construction des informations
		this.comic=comic;
		this.name = comic.getName();
		this.imageURL = comic.getImage();
		this.date = comic.getDate();
		this.description = comic.getDescription();
		this.publisher = comic.getPublisher();
		this.issue = comic.getIssue();
		// Création de l'image
		try {
			BufferedImage temp = ImageIO.read(new URL(this.imageURL));
			frame.setSize(1200, 700);
			this.image = temp.getScaledInstance(400, 650, Image.SCALE_DEFAULT);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel image = new JLabel(new ImageIcon(this.image));
		image.setBorder(new EmptyBorder(0, 10, 0, 10));

		// Création de la zone d'information
		JPanel info = new JPanel();
		BorderLayout panelBorder = new BorderLayout();
		panelBorder.setVgap(10);
		info.setBorder(new EmptyBorder(0,0,0,10));
		info.setLayout(panelBorder);

		JLabel name = new JLabel(this.name);
		name.setFont(new Font("Verdana", Font.BOLD, 24));
		name.setHorizontalAlignment(JLabel.CENTER);
		JLabel date = new JLabel("Published in " + this.date);
		date.setFont(new Font("Verdana", Font.PLAIN, 16));
		JLabel publisher = new JLabel("Publisher : " + this.publisher);
		publisher.setFont(new Font("Verdana", Font.PLAIN, 16));

		// Création de la description
		JPanel description = new JPanel();
		description.setLayout(new BorderLayout());
		JEditorPane blabla = new JEditorPane();
		blabla.setEditable(false);
		blabla.setContentType("text/html");
		blabla.setText("<html>" + this.description + "</html>");
		JScrollPane scroll = new JScrollPane(blabla);
		scroll.setVisible(true);
		description.add(scroll);

		JPanel header = new JPanel();
		BorderLayout headerBorder = new BorderLayout();
		headerBorder.setVgap(10);
		header.setLayout(headerBorder);
		header.add(name, BorderLayout.NORTH);
		header.add(date, BorderLayout.CENTER);
		header.add(publisher, BorderLayout.SOUTH);
		
		// Boutons
			// Création des boutons
		JButton btnRead = new JButton("Read");
		JButton btnWantToRead = new JButton("Want to read");
		JButton btnInProgress = new JButton("In progress");
		btnLike = new JButton("Like");
			// Création des listeners
		DetailsButtonsListener mouselistener = new DetailsButtonsListener(this);
		DetailsButtonLikeListener likelistener = new DetailsButtonLikeListener(this);
			//Ajout des listeners
		btnRead.addMouseListener(mouselistener);
		btnWantToRead.addMouseListener(mouselistener);
		btnInProgress.addMouseListener(mouselistener);
		btnLike.addMouseListener(likelistener);
		
		JPanel btn = new JPanel();
		
		if(User.isPremium()) {
			if(checkIfLiked()) {
				btnLike.setText("Liked");
			}
			btn.add(btnRead);
			btn.add(btnWantToRead);
			btn.add(btnInProgress);
			btn.add(btnLike);
		}
		

		JPanel buttonFollow = new JPanel();
		buttonFollow.setLayout(new BorderLayout());
		FollowingListener fl =new FollowingListener(this,this.comic);
		JButton next = new JButton("Next in the volume");
		JButton previous = new JButton("Previously in the volume");
		next.addMouseListener(fl);
		previous.addMouseListener(fl);
		buttonFollow.add(next,BorderLayout.NORTH);
		buttonFollow.add(previous,BorderLayout.SOUTH);
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		bottom.add(btn,BorderLayout.WEST);
		bottom.add(buttonFollow,BorderLayout.EAST);
		bottom.setBorder(new EmptyBorder(0, 0,10,10));
		
		
		info.add(header, BorderLayout.NORTH);
		info.add(description, BorderLayout.CENTER);
		info.add(bottom,BorderLayout.SOUTH);

		frame.getContentPane().add(image, BorderLayout.WEST);
		frame.getContentPane().add(info, BorderLayout.CENTER);

	}
	public void setLikeBtn(String like) {
		btnLike.setText(like);
	}
	public long getIssue() {
		return comic.getIssue();
	}
	public boolean checkIfLiked() {
		Connection connection = null;
		try {
			// Chargement du pilote SQLite
			Class.forName("org.sqlite.JDBC");

			// Connexion à la base de données
			connection = DriverManager.getConnection("jdbc:sqlite:Account.db");

			// Création d'une requête
			String query = "SELECT * FROM Account_comic_like WHERE Login = '" + User.getLogin() + "' AND comicId = "+this.issue;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {

				return true;
			} else {
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
