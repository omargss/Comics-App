package DisplayScreen;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Objects.Comic;

public class Details {

	public Details(Comic comic) {

		JFrame frame = new JFrame(); // initialiser la fenetre 
		JPanel panelImage = new JPanel(); // initialiser le panel pour l'image
		JLabel image = new JLabel(); // le componant pour image
		frame.setVisible(true); 
		frame.setSize(550,600); // dimension de l'image
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // nous permet de fermer une seule fenêtre de details à la fois
	    frame.setResizable(false); // dimensions de la fenêtre ne sont pas changeable 
		ImageIcon img;
		try {
			URL url_img = new URL(comic.getImage()); // récupérer l'url de l'image
			BufferedImage imageBrute = ImageIO.read(url_img); 
			Image imageResize = imageBrute.getScaledInstance(250, 350, Image.SCALE_DEFAULT);
			img = new ImageIcon(imageResize);
			// finalement en récupère l'image redimensionnée
		} catch (IOException e) {
			// url dans le cas si l'image ne se charge pas
			img = new ImageIcon(comic.getImage());
		}
		image.setIcon(img);
		panelImage.add(image); // ajout de l'image dans le paneau
		frame.add(panelImage); // ajout du paneau dans la fenêtre
	}
}
