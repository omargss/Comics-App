package Listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import DisplayScreen.Window;
import GetData.GetComicsData;
import Objects.Comic;

public class Keyboard extends KeyAdapter {
	Window screen = null;

	@SuppressWarnings("unused")
	private Keyboard() {
	}

	public Keyboard(Window window) {
		super();
		this.screen = window;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String filter = screen.get_radio_value();
			String search = screen.get_text();
			List<Comic> dataList = null;
			System.out.println(filter);
			switch(filter) {
			case "Title":
				dataList=GetComicsData.getComicsData(search, null, null, null);
				break;
			case "Publisher":
				dataList=GetComicsData.getComicsData(null, search, null, null);
				break;
			}
			screen.set_results(dataList);
			screen.set_text("");
		}
	}
}