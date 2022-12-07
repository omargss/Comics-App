package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

import DisplayScreen.Details;
import DisplayScreen.Window;
import GetData.GetComicsData;
import Objects.Comic;

public class Mouse extends MouseAdapter {
	Window screen = null;
	List<Comic> dataList = null;

	public Mouse(Window window) {
		super();
		this.screen = window;
	}

	public void mouseClicked(MouseEvent e) {
			if(e.getSource() == screen.get_button()) {
				String filter_title_publisher = screen.get_radio_value();
				String search = screen.get_text();
				String filter = screen.get_combobox_value();
				String yearMin= screen.getDropDownYearsMin();
				String yearMax = screen.getDropDownYearsMax();
				List<Comic> dataList = null;
				System.out.println(filter);
				switch(filter_title_publisher) {
				case "Title":
					dataList=GetComicsData.getComicsData(search,null,null,null,yearMin,yearMax);
					break;
				case "Publisher":
					dataList=GetComicsData.getComicsData(null, search, null, null,yearMin,yearMax);
					break;
				}
				// Tri croissant décroissant
				switch(filter) {
				case "nom croissant":
					Collections.sort(dataList,Comic.NameComparator);
					break;
				case "nom decroissant":
					Collections.sort(dataList,Comic.NameComparatordec);
					break;
				case "date croissante":
					Collections.sort(dataList,Comic.DateComparator);
					break;
				case "date decroissante":
					Collections.sort(dataList,Comic.DateComparatordec);
					break;
				case "publisher croissant":
					Collections.sort(dataList,Comic.PublisherComparator);
					break;
				case "publisher decroissant":
					Collections.sort(dataList,Comic.PublisherComparatordec);
					break;
				}				
				screen.set_results(dataList);
				screen.set_text("");
		}
		else if(e.getSource()==screen.get_area()) {
			screen.set_text("");
		} else if (e.getSource() == screen.get_jtable()) {
			
			int row = screen.get_jtable().rowAtPoint(e.getPoint());
			int col = screen.get_jtable().columnAtPoint(e.getPoint());
			System.out.println(col);
			if (col == 4) {
				Details details = new Details(this.dataList.get(row));

			} else if (e.getSource() == screen.get_area()) {
				if (e.getSource() == screen.get_button()) {
					String filter = screen.get_radio_value();
					String search = screen.get_text();

					System.out.println(filter);
					switch (filter) {
					case "Title":
						dataList = GetComicsData.getComicsData(search, null, null, null);
						break;
					case "Publisher":
						dataList = GetComicsData.getComicsData(null, search, null, null);
						break;
					}
					screen.set_results(dataList);
					screen.set_text("");
				} else if (e.getSource() == screen.get_area()) {
					if (e.getSource() == screen.get_button()) {
						String filter = screen.get_radio_value();
						String search = screen.get_text();

						System.out.println(filter);
						switch (filter) {
						case "Title":
							dataList = GetComicsData.getComicsData(search, null, null, null, screen.getDropDownYearsMin(), screen.
							getDropDownYearsMax());
							break;
						case "Publisher":
							dataList = GetComicsData.getComicsData(null, search, null, null, screen.getDropDownYearsMin(), screen.getDropDownYearsMax());
							break;
						}
						screen.set_results(dataList);
						screen.set_text("");
					} else if (e.getSource() == screen.get_area()) {
						screen.set_text("");
					} else if (e.getSource() == screen.get_area()) {
						screen.set_text("");
					}
				}
			}
		}
	}
}
