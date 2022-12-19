package Listeners;
import DisplayScreen.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MainWindowListener extends MouseAdapter {
	MainWindow window = null;

	@SuppressWarnings("unused")
	private MainWindowListener() {
	}

	public MainWindowListener(MainWindow w) {
		super();
		window = w;
	}

	public void mouseClicked(MouseEvent e) {
		if((((JButton) e.getSource()).getText()).equals("Comic search")) {
			window.setDisplayedPanel(0);
		}
		if((((JButton) e.getSource()).getText()).equals("Character search")) {
			window.setDisplayedPanel(1);
		}
		if((((JButton) e.getSource()).getText()).equals("Login")) {
			window.setDisplayedPanel(2);
		}
	}

}