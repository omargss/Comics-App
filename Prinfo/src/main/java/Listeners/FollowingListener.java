package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import DisplayScreen.DetailsComic;
import GetData.GetFollowingData;
import Objects.Comic;

public class FollowingListener extends MouseAdapter{
	DetailsComic details;
	Comic comic;
	int step;
	
	public FollowingListener(DetailsComic detailsComic,Comic comic) {
		super();
		this.details=detailsComic;
		this.comic=comic;
		//System.out.println(comic);
	}
	
	public static void noComicFound() {
		JOptionPane.showMessageDialog(null, "no comic found");
	}
	public void mouseClicked(MouseEvent e) {
		if((((JButton) e.getSource()).getText()).equals("Previously in the volume")) {
			try {
				this.comic=GetFollowingData.previousComic(this.comic.getIdVolume(), this.comic.getIssueNumber());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		else if((((JButton) e.getSource()).getText()).equals("Next in the volume")) {
			try {
				this.comic=GetFollowingData.nextComic(this.comic.getIdVolume(), this.comic.getIssueNumber());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		if(comic == null) {
			noComicFound();
		}
		else {
			details = new DetailsComic(comic);
		}
		
	}
}