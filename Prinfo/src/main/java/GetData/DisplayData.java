package GetData;

import Objects.Comic;
//f6929d31c63612dd656e42295cc122010ac74c1c
import java.util.*;


public class DisplayData{
	public static String Display(List<Comic> listeData) {
		String retour="";
		for(int i=0;i<listeData.size();i++) {
			retour+="Nom : "+listeData.get(i).getName()+"\n";
			retour+="Date : "+listeData.get(i).getDate()+"\n";
			retour+="Publieur : "+listeData.get(i).getPublisher()+"\n";
			retour+="Image : "+listeData.get(i).getImage()+"\n";
			retour+="URL : "+listeData.get(i).getUrl()+"\n";
			retour+="\n";
		}
		return(retour);
	}
}