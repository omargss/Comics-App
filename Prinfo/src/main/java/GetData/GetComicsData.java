package GetData;

//f6929d31c63612dd656e42295cc122010ac74c1c
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.*;
import java.util.*;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetComicsData {

	public static void main(String[] args) {
		getComicsData("hulk");

	}

	public static void getComicsData(String title) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
				"https://comicvine.gamespot.com/api/volumes/?api_key=f6929d31c63612dd656e42295cc122010ac74c1c&format=json&sort=name:asc&filter=name:"
						+ title))
				.build();

		HttpResponse<String> response;
		try {
			List<List<String>> list = new ArrayList<List<String>>();
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.body());
			JSONArray results = (JSONArray) jsonObject.get("results");

			Iterator<JSONObject> iterator = results.iterator();

			while (iterator.hasNext()) {
				// plus tard il faudra mettre sous forme d'une liste d'objets de classe comics, à voir j'ai pas le temps
				ArrayList<String> listToAdd = new ArrayList<String>();
				listToAdd.add((String)iterator.next().get("name")); // 0
				listToAdd.add((String)iterator.next().get("site_detail_url")); // 1
				listToAdd.add((String)iterator.next().get("date_added"));
				listToAdd.add((String)iterator.next().get("description"));
				// Problème sur les publishers, certains comics n'ont pas de publisher, à voir comment faire


				list.add(listToAdd);
			}
			for(int j = 0; j < list.size();j++)
				System.out.println(list.get(j).get(0)) ;

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

	}

}
