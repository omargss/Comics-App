package Listeners;
import DisplayScreen.*;
import GetData.GetComicsData;
import Objects.Comic;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

public class ComicSearchListener extends MouseAdapter {

    ComicSearchPanel cspanel = null;

    public ComicSearchListener(ComicSearchPanel csp) {
        super();
        cspanel = csp;
    }

    public void mouseClicked(MouseEvent e) {
		List<Comic> dataList = null;
        Object o = e.getSource();
        if (o instanceof JButton)
        {
            JButton b = (JButton) o;
            // Si on clique sur le bouton de recherche
            if (b.getText().equals("Search")) {
                String search = cspanel.getTextField().getText();
                String yearMin = cspanel.getDropDownYearsMin();
                String yearMax = cspanel.getDropDownYearsMax();

                String titleOrPublisherChoice = cspanel.getRadioValue();
                // ce string renvoie "Title" ou "Publisher" selon le choix
                if (cspanel.getRadioValue() == "Title") {
					dataList=GetComicsData.getComicsDataByName(search,null,null,yearMin,yearMax);
                } else {
					dataList=GetComicsData.getComicsDataByPublisher(search,null,null,yearMin,yearMax);
                }
                cspanel.updateResultTable(dataList);
            }
        }
    }
}