package DisplayScreen;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Objects.Comic;

public class Details extends JFrame {
	 
	Comic comic;
	 
	 public Details(Comic comic){
		 this.comic=comic;
		 initComponents();
		 
	  //System.out.println(comic.getName());// verifie qu'on récupère le bon comic
	  
}


private void initComponents() {

	jPanelDetails = new javax.swing.JPanel();
    jPanelDescription = new javax.swing.JPanel();
    jPanelPrice = new javax.swing.JPanel();
    jPanelPublisher = new javax.swing.JPanel();
    jTitle = new javax.swing.JLabel();
    jDate = new javax.swing.JLabel();
    jPublisher = new javax.swing.JLabel();
    jUrl = new javax.swing.JLabel();
    jPanelImage = new javax.swing.JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // nous permet de fermer une seule fenêtre de details à la fois
    setResizable(false);
    setVisible(true);

    jPanelDescription.setBackground(new java.awt.Color(204, 204, 204));

    javax.swing.GroupLayout jPanelDescriptionLayout = new javax.swing.GroupLayout(jPanelDescription);
    jPanelDescription.setLayout(jPanelDescriptionLayout);
    jPanelDescriptionLayout.setHorizontalGroup(
        jPanelDescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 327, Short.MAX_VALUE)
    );
    jPanelDescriptionLayout.setVerticalGroup(
        jPanelDescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 265, Short.MAX_VALUE)
    );

    jPanelPrice.setBackground(new java.awt.Color(204, 204, 204));

    javax.swing.GroupLayout jPanelPriceLayout = new javax.swing.GroupLayout(jPanelPrice);
    jPanelPrice.setLayout(jPanelPriceLayout);
    jPanelPriceLayout.setHorizontalGroup(
        jPanelPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 159, Short.MAX_VALUE)
    );
    jPanelPriceLayout.setVerticalGroup(
        jPanelPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );

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
    jUrl.setText("URL : " + comic.getUrl() );
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
    JLabel jImage=null;
    try {
        URL url = new URL(comic.getImage());       // pour afficher l'image ... faut voir la taille
        BufferedImage image = ImageIO.read(url);
        jImage = new JLabel(new ImageIcon(image));
        image = ImageIO.read(url);
        this.add(jImage);

    } catch (IOException e) {
        e.printStackTrace();

    }
    
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

    javax.swing.GroupLayout jPanelDetailsLayout = new javax.swing.GroupLayout(jPanelDetails);
    jPanelDetails.setLayout(jPanelDetailsLayout);
    jPanelDetailsLayout.setHorizontalGroup(
        jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelDetailsLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelDetailsLayout.createSequentialGroup()
                    .addComponent(jPanelPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelDetailsLayout.createSequentialGroup()
                    .addComponent(jPanelDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanelPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanelDetailsLayout.setVerticalGroup(
        jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelDetailsLayout.createSequentialGroup()
            .addGap(12, 12, 12)
            .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(0, 0, 0))
    );

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
private javax.swing.JLabel jImage;
private javax.swing.JPanel jPanelDescription;
private javax.swing.JPanel jPanelDetails;
private javax.swing.JPanel jPanelImage;
private javax.swing.JPanel jPanelPrice;
private javax.swing.JPanel jPanelPublisher;
private javax.swing.JLabel jPublisher;
private javax.swing.JLabel jTitle;
private javax.swing.JLabel jUrl;
// End of variables declaration                   
}
