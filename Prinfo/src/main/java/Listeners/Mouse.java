package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import DisplayScreen.Window;
import GetData.GetComicsData;
import Objects.Comic;

public class Mouse extends MouseAdapter {
	Window screen = null;

	@SuppressWarnings("unused")
	private Mouse() {
	}

	public Mouse(Window window) {
		super();
		this.screen = window;
	}

	public void mouseClicked(MouseEvent e) {
			if(e.getSource() == screen.get_button()) {
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
		else if(e.getSource()==screen.get_area()) {
			screen.set_text("");
		}
	}
}