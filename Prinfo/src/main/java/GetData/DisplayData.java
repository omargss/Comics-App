package GetData;

import Objects.Comic;
//f6929d31c63612dd656e42295cc122010ac74c1c
import java.util.*;


public class DisplayData{
	public static String Display(List<Comic> dataList) {
		String retour="";
		for(int i=0;i<dataList.size();i++) {
			retour+="Nom : "+dataList.get(i).getName()+"\n";
			retour+="Date : "+dataList.get(i).getDate()+"\n";
			retour+="Publieur : "+dataList.get(i).getPublisher()+"\n";
			retour+="Image : "+dataList.get(i).getImage()+"\n";
			retour+="URL : "+dataList.get(i).getUrl()+"\n";
			retour+="\n";
		}
		return(retour);
	}
}