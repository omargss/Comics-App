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

	public static void main(String[] args) {
		getComicsData("hulk", "name:asc", "25"); // bizzare, on a 5 fois moins de résultats que prévu... à voir

	}

	public static List<Comic> getComicsData(String name, String sort, String limit) {
		HttpClient client = HttpClient.newHttpClient();
		String name_formatted = name.replace(' ','+');
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
		"https://comicvine.gamespot.com/api/volumes/?api_key=f6929d31c63612dd656e42295cc122010ac74c1c&format=json&sort="+sort+"&limit="+limit+"&filter=name:"+ name_formatted)).build();

		HttpResponse<String> response;
		try {
			List<Comic> list = new ArrayList<Comic>();
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
				JSONObject publisher = (JSONObject)iterator.next().get("publisher");
				comic.setPublisher((String)publisher.get("name"));
				// Problème sur les publishers, certains comics n'ont pas de publisher, à voir comment faire
				list.add(comic);
			}
			return(list);
			/*for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i).getName());
				System.out.println(list.get(i).getDate());
				System.out.println(list.get(i).getPublisher());
				System.out.println(list.get(i).getImage());
				System.out.println(list.get(i).getUrl());
			}*/
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
		return(null);
	}

}