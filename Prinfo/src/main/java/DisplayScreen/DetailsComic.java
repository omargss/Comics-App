package DisplayScreen;
import javax.swing.JFrame;

import Objects.Comic;

public class DetailsComic extends JFrame {
	
	public DetailsComic(Comic comic) {
		/*
		 * Contenu d'un comic :
		 * 	- nom
		 * 	- image
		 * 	- date
		 * 	- publisher
		 * 	- volume
		 * 	- description
		 */
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(600, 300);
		
	}
}


