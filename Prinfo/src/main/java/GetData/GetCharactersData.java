package GetData;

import Objects.Character;
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

public class GetCharactersData {
	private static String apiKey = "f6929d31c63612dd656e42295cc122010ac74c1c";
	// Méthode permettant de récupérer une liste de personnages par titre
	public static List<Character> GetCharacterData(String title) {
		HttpClient client = HttpClient.newHttpClient();
		String APIRequest = "https://comicvine.gamespot.com/api/characters/?api_key=" + apiKey
				+ "&format=json&field_list=name,publisher,image,deck&sort=count_of_issue_appearances:desc";
		
		String title_formatted = title.replace(' ', ',').replace("%", "%25").replace("'", "%27").replace("?", "%3F")
				.replace("!", "%21").replace(":", "%3A").replace(",", "%2C").replace("&", "%26");
		APIRequest += "&filter=name:" + title_formatted;

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIRequest)).build();
		HttpResponse<String> response;
		List<Character> list = new ArrayList<Character>();

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.body());
			JSONArray results = (JSONArray) jsonObject.get("results");

			Iterator<JSONObject> iterator = results.iterator();

			while (iterator.hasNext()) {
				try {
					Character character = new Character();
					character.setName((String) iterator.next().get("name"));
					JSONObject image = (JSONObject) iterator.next().get("image");
					character.setImage((String) image.get("original_url"));
					JSONObject publisher = (JSONObject) iterator.next().get("publisher");
					character.setPublisher((String) publisher.get("name"));
					character.setDescription((String) iterator.next().get("deck"));
					list.add(character);
				} catch (NoSuchElementException nsee) {
					nsee.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}



}