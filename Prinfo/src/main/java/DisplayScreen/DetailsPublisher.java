package DisplayScreen;
import javax.swing.JFrame;

import Objects.Publisher;

public class DetailsPublisher extends JFrame {
	
	public DetailsPublisher(Publisher publisher) {
		/*
		 * Contenu d'un publisher :
		 * 	- nom
		 * 	- image
		 *  - description
		 */
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(600, 300);
		
	}
}


