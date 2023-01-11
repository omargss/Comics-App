package DisplayScreen;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Objects.Comic;

public class DetailsComic extends JFrame {

	String name;
	String imageURL;
	Image image;
	String date;
	String publisher;
	String description = "null à plus tard";

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
		this.name = comic.getName();
		this.imageURL = comic.getImage();
		this.date = comic.getDate();
		this.description=comic.getDescription();
		this.publisher = comic.getPublisher();

		// Création de l'image
		try {
			BufferedImage temp = ImageIO.read(new URL(this.imageURL));
			frame.setSize(temp.getWidth() + 500, temp.getHeight() + 50);
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

		info.add(header, BorderLayout.NORTH);
		info.add(description, BorderLayout.CENTER);

		frame.add(image, BorderLayout.WEST);
		frame.add(info, BorderLayout.CENTER);

	}
}
