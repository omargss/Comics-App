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
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import Listeners.DetailsButtonLikeListener;
import Listeners.DetailsButtonsListener;
import Listeners.MainWindowListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Details extends JFrame {
	
	Comic comic;
	public Details(Comic comic) {
		
		 this.comic=comic;
		 initComponents();
		 
	}
	
	private void initComponents() {

		jPanelDetails = new JPanel();
	    jPanelPublisher = new JPanel();
	    jTitle = new JLabel();
	    jDate = new JLabel();
	    jPublisher = new JLabel();
	    jUrl = new JLabel();
	    JPanel jPanelImage = new JPanel();
	    JLabel jImage = new JLabel();

	    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // nous permet de fermer une seule fenêtre de details à la fois
	    setResizable(false);
	    setVisible(true);

//	    javax.swing.GroupLayout jPanelDescriptionLayout = new javax.swing.GroupLayout(jPanelDescription);
//	    jPanelDescription.setLayout(jPanelDescriptionLayout);
//	    jPanelDescriptionLayout.setHorizontalGroup(
//	        jPanelDescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//	        .addGap(0, 327, Short.MAX_VALUE)
//	    );
//	    jPanelDescriptionLayout.setVerticalGroup(
//	        jPanelDescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//	        .addGap(0, 265, Short.MAX_VALUE)
//	    );
//
//	    jPanelPrice.setBackground(new java.awt.Color(204, 204, 204));
//
//	    javax.swing.GroupLayout jPanelPriceLayout = new javax.swing.GroupLayout(jPanelPrice);
//	    jPanelPrice.setLayout(jPanelPriceLayout);
//	    jPanelPriceLayout.setHorizontalGroup(
//	        jPanelPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//	        .addGap(0, 159, Short.MAX_VALUE)
//	    );
//	    jPanelPriceLayout.setVerticalGroup(
//	        jPanelPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//	        .addGap(0, 0, Short.MAX_VALUE)
//	    );

	    jPanelPublisher.setBackground(new java.awt.Color(204, 204, 204));
	    jPanelPublisher.setLayout(new java.awt.GridLayout(4, 1, 0, 10));


	    jTitle.setFont(new java.awt.Font("Sitka Heading", 0, 24)); // NOI18N
	    jTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	    jTitle.setText(comic.getName());
	    jPanelPublisher.add(jTitle);
	    
	    
	    jDate.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
	    jDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	    jDate.setText("publié le : " + comic.getDate().split(" ")[0]);
	    jPanelPublisher.add(jDate);

	    jPublisher.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
	    jPublisher.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	    jPublisher.setText("édition : " + comic.getPublisher());
	    jPanelPublisher.add(jPublisher);

	    jUrl.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
	    jUrl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	    jUrl.setText("Volume : " + comic.getVolume() );
	    jPanelPublisher.add(jUrl);
	    
	    

	    javax.swing.GroupLayout jPanelPublisherLayout = new javax.swing.GroupLayout(jPanelPublisher);
	    jPanelPublisher.setLayout(jPanelPublisherLayout);
	    jPanelPublisherLayout.setHorizontalGroup(
	        jPanelPublisherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(jPanelPublisherLayout.createSequentialGroup()
	            .addContainerGap()
	            .addGroup(jPanelPublisherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addComponent(jDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(jTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(jPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(jUrl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	            .addContainerGap())
	    );
	    jPanelPublisherLayout.setVerticalGroup(
	        jPanelPublisherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPublisherLayout.createSequentialGroup()
	            .addContainerGap(30, Short.MAX_VALUE)
	            .addComponent(jTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addGap(18, 18, 18)
	            .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	            .addComponent(jPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	            .addComponent(jUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addGap(34, 34, 34))
	    );

	    jPanelImage.setBackground(new java.awt.Color(204, 204, 204)); 
	    
	    ImageIcon img;
	    try {
	        URL url = new URL(comic.getImage());       // pour afficher l'image ... faut voir la taille
	        BufferedImage imageBrute = ImageIO.read(url);
	        Image imageResize = imageBrute.getScaledInstance(250, 350, Image.SCALE_DEFAULT);
	        img = new ImageIcon(imageResize);

	    } catch (IOException e) {
	        e.printStackTrace();
	        img = new ImageIcon(comic.getImage());
	    }
	    
	    jImage.setIcon(img);
	    jPanelImage.add(jImage);
	    
	    javax.swing.GroupLayout jPanelImageLayout = new javax.swing.GroupLayout(jPanelImage);
	    jPanelImage.setLayout(jPanelImageLayout);
	    jPanelImageLayout.setHorizontalGroup(
	        jPanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelImageLayout.createSequentialGroup()
	            .addContainerGap()
	            .addComponent(jImage, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
	            .addContainerGap())
	    );
	    jPanelImageLayout.setVerticalGroup(
	        jPanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelImageLayout.createSequentialGroup()
	            .addContainerGap()
	            .addComponent(jImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	            .addContainerGap())
	    );
	    
	    JButton btnLu = new JButton("Lu");
	    
	    JButton btnEnvieDeLire = new JButton("Envie de lire");
	    
	    JButton btnEnCoursLecture = new JButton("En cours de lecture");
	    
	    JButton btnLike = new JButton("J'aime");
	    
	    DetailsButtonsListener mouselistener = new DetailsButtonsListener(this);
	    DetailsButtonLikeListener likelistener = new DetailsButtonLikeListener(this);
	    
	    btnLu.addMouseListener(mouselistener);
	    btnEnvieDeLire.addMouseListener(mouselistener);
	    btnEnCoursLecture.addMouseListener(mouselistener);
	    
	    btnLike.addMouseListener(likelistener);

	    javax.swing.GroupLayout jPanelDetailsLayout = new javax.swing.GroupLayout(jPanelDetails);
	    jPanelDetailsLayout.setHorizontalGroup(
	    	jPanelDetailsLayout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(jPanelDetailsLayout.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(jPanelDetailsLayout.createParallelGroup(Alignment.TRAILING)
	    				.addGroup(jPanelDetailsLayout.createSequentialGroup()
	    					.addComponent(jPanelPublisher, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(jPanelImage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(jPanelDetailsLayout.createSequentialGroup()
	    					.addComponent(btnEnCoursLecture)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(btnEnvieDeLire)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(btnLu))
	    				.addComponent(btnLike))
	    			.addContainerGap())
	    );
	    jPanelDetailsLayout.setVerticalGroup(
	    	jPanelDetailsLayout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(jPanelDetailsLayout.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(btnLike)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(jPanelDetailsLayout.createParallelGroup(Alignment.LEADING, false)
	    				.addComponent(jPanelImage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    				.addComponent(jPanelPublisher, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    			.addGroup(jPanelDetailsLayout.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(btnEnCoursLecture)
	    				.addComponent(btnEnvieDeLire)
	    				.addComponent(btnLu))
	    			.addContainerGap())
	    );
	    jPanelDetails.setLayout(jPanelDetailsLayout);

	    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setHorizontalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addComponent(jPanelDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    );
	    layout.setVerticalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addComponent(jPanelDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    );
	    pack();

	}

	// Variables declaration - do not modify                     
	private javax.swing.JLabel jDate;
	private javax.swing.JPanel jPanelDetails;
	private javax.swing.JPanel jPanelImage;
	private javax.swing.JPanel jPanelPublisher;
	private javax.swing.JLabel jPublisher;
	private javax.swing.JLabel jTitle;
	private javax.swing.JLabel jUrl;
		}



