package DisplayScreen;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Listeners.DetailsButtonLikeListener;
import Listeners.DetailsButtonsListener;
import Listeners.FollowingListener;
import Objects.Comic;

public class DetailsComic extends JFrame {

	String name;
	String imageURL;
	Image image;
	String date;
	String publisher;
	String description = "null à plus tard";
	Comic comic;

	public DetailsComic(Comic comic) {
		/*
		 * Contenu d'un comic : - volume
		 */

		// Définition de la fenetre à afficher
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
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

		// Création de l'image
		try {
			BufferedImage temp = ImageIO.read(new URL(this.imageURL));
			frame.setSize(temp.getWidth() + 800, temp.getHeight() + 50);
			this.image = temp.getScaledInstance(temp.getWidth(), temp.getHeight(), Image.SCALE_DEFAULT);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel image = new JLabel(new ImageIcon(this.image));

		// Création de la zone d'information
		JPanel info = new JPanel();
		info.setLayout(new BorderLayout());

		JLabel name = new JLabel(this.name);
		JLabel date = new JLabel("Published in " + this.date);
		JLabel publisher = new JLabel("Publisher : " + this.publisher);

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
		header.setLayout(new BorderLayout());
		header.add(name, BorderLayout.NORTH);
		header.add(date, BorderLayout.CENTER);
		header.add(publisher, BorderLayout.SOUTH);
		
		// Boutons
			// Création des boutons
		JButton btnLu = new JButton("Lu");
		JButton btnEnvieDeLire = new JButton("Envie de lire");
		JButton btnEnCoursLecture = new JButton("En cours de lecture");
		JButton btnLike = new JButton("J'aime");
			// Création des listeners
		DetailsButtonsListener mouselistener = new DetailsButtonsListener(this);
		DetailsButtonLikeListener likelistener = new DetailsButtonLikeListener(this);
			//Ajout des listeners
		btnLu.addMouseListener(mouselistener);
		btnEnvieDeLire.addMouseListener(mouselistener);
		btnEnCoursLecture.addMouseListener(mouselistener);
		btnLike.addMouseListener(likelistener);
		
		JPanel btn = new JPanel();
		btn.add(btnLu);
		btn.add(btnEnvieDeLire);
		btn.add(btnEnCoursLecture);
		btn.add(btnLike);
		

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
		
		
		info.add(header, BorderLayout.NORTH);
		info.add(description, BorderLayout.CENTER);
		info.add(bottom,BorderLayout.SOUTH);

		frame.add(image, BorderLayout.WEST);
		frame.add(info, BorderLayout.CENTER);

	}
	
	public long getIssue() {
		return comic.getIssue();
	}
}
