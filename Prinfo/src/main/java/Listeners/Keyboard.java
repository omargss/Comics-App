package Listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Affichage.Fenetre;
import GetData.DisplayData;


public class Keyboard extends KeyAdapter {
	Fenetre ecran = null;

	@SuppressWarnings("unused")
	private Keyboard() {
	}

	public Keyboard(Fenetre fenetre) {
		super();
		this.ecran = fenetre;
	}

	@Override
	public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
			ecran.set_results(DisplayData.DisplayDate(ecran.get_text()));
			ecran.set_text("");
        }
		}
	}