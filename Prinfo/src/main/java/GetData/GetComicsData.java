package GetData;
import Objects.Comic;
//f6929d31c63612dd656e42295cc122010ac74c1c
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetComicsData {
	private static String apiKey = "f6929d31c63612dd656e42295cc122010ac74c1c";

	public static void main(String[] args) {
		//getComicsData("hulk", "name:asc", "25"); // bizzare, on a 5 fois moins de résultats que prévu... à voir

	}

	public static List<Comic> getComicsData(String title, String publisher_name, String sort, String limit) {
		HttpClient client = HttpClient.newHttpClient();		
		String APIRequest="https://comicvine.gamespot.com/api/volumes/?api_key=f6929d31c63612dd656e42295cc122010ac74c1c&format=json&sort="+sort+"&limit="+limit;
		String title_formatted=null;
		String publisher_formatted=null;
		if(title!=null) {
			title_formatted = title.replace(' ','+').replace("'", "%27").replace("?", "%3F").replace("!", "%21").replace(":", "%3A").replace(",", "%2C").replace("&", "%26");
			APIRequest+="&filter=name:"+ title_formatted;
		}
		if(publisher_name!=null){
			publisher_formatted = publisher_name.replace(' ','+').replace("'", "%27").replace("?", "%3F").replace("!", "%21").replace(":", "%3A").replace(",", "%2C").replace("&", "%26");
			APIRequest+="/publisher&filter=name:"+publisher_formatted;
		}
		System.out.println(APIRequest);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIRequest)).build();

		HttpResponse<String> response;
		List<Comic> list = new ArrayList<Comic>();
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.body());
			JSONArray results = (JSONArray) jsonObject.get("results");

			Iterator<JSONObject> iterator = results.iterator();

			while (iterator.hasNext()) {
				Comic comic = new Comic();
				comic.setDate((String)iterator.next().get("date_added"));
				comic.setName((String)iterator.next().get("name"));
				comic.setUrl((String)iterator.next().get("site_detail_url"));
				JSONObject image = (JSONObject)iterator.next().get("image");
				comic.setImage((String)image.get("original_url"));
				//JSONObject volume= (JSONObject)iterator.next().get("volume");
				//comic.setVolume((String)volume.get("name")); // peut engendrer des erreurs si c'est null
				// Problème sur les publishers, certains comics n'ont pas de publisher, à voir comment faire
				JSONObject publisher = (JSONObject)iterator.next().get("publisher");
				comic.setPublisher((String)publisher.get("name"));
				list.add(comic);
			}
			return(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}