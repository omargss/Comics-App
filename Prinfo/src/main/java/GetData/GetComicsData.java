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

	// Méthode permettant de récupérer une liste de comics par titre
	public static List<Comic> getComicsDataByName(String title, String sort, String limit, String yearMin,
			String yearMax) {
		HttpClient client = HttpClient.newHttpClient();
		String APIRequest = "https://comicvine.gamespot.com/api/issues/?api_key=" + apiKey
				+ "&format=json&field_list=description,name,cover_date,image,volume&sort=" + sort + "&limit="
				+ limit;
		String title_formatted = title.replace(' ', ',').replace("%", "%25").replace("'", "%27").replace("?", "%3F")
				.replace("!", "%21").replace(":", "%3A").replace(",", "%2C").replace("&", "%26");
		APIRequest += "&filter=name:" + title_formatted;
		APIRequest += ",cover_date:" + yearMin + "-01-01%7C" + yearMax + "-12-31";

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
				try {
					Comic comic = new Comic();
					comic.setDate((String) iterator.next().get("cover_date"));
					comic.setName((String) iterator.next().get("name"));
					JSONObject image = (JSONObject) iterator.next().get("image");
					comic.setImage((String) image.get("original_url"));
					JSONObject volume = (JSONObject) iterator.next().get("volume");
					comic.setVolume((String) volume.get("name"));
					comic.setDescription((String) iterator.next().get("description"));
					// Publisher subrequest
					String APIPublisherRequest = "https://comicvine.gamespot.com/api/volumes/?api_key=f6929d31c63612dd656e42295cc122010ac74c1c&format=json&filter=id:"
							+ volume.get("id") + "&field_list=publisher";
					HttpRequest publisherRequest = HttpRequest.newBuilder().uri(URI.create(APIPublisherRequest))
							.build();
					HttpResponse<String> publisherResponse = client.send(publisherRequest,
							HttpResponse.BodyHandlers.ofString());
					JSONParser publisherParser = new JSONParser();
					JSONObject publisherjsonObject = (JSONObject) publisherParser.parse(publisherResponse.body());
					JSONArray publisherResults = (JSONArray) publisherjsonObject.get("results");
					JSONObject publisher = (JSONObject) ((JSONObject) publisherResults.get(0)).get("publisher");
					comic.setPublisher((String) publisher.get("name"));
					// End of publisher subrequest
					list.add(comic);
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

	
	
	
	// Méthode permettant de récupérer une liste de comics par publishers
	public static List<Comic> getComicsDataByPublisher(String publisher_name, String sort, String limit,String yearMin, String yearMax) {
		HttpClient client = HttpClient.newHttpClient();
		String APIRequest = "https://comicvine.gamespot.com/api/issues/?api_key=" + apiKey
				+ "&format=json&field_list=description,name,cover_date,image,volume&sort=" + sort + "&limit="
				+ limit;
		String publisher_formatted = publisher_name.replace(' ', ',').replace("%", "%25").replace("'", "%27").replace("?", "%3F")
				.replace("!", "%21").replace(":", "%3A").replace(",", "%2C").replace("&", "%26");
		APIRequest += "&filter=publisher.name:" + publisher_formatted;
		APIRequest += ",cover_date:" + yearMin + "-01-01%7C" + yearMax + "-12-31";

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
				try {
					Comic comic = new Comic();
					comic.setDate((String) iterator.next().get("cover_date"));
					comic.setName((String) iterator.next().get("name"));
					JSONObject image = (JSONObject) iterator.next().get("image");
					comic.setImage((String) image.get("original_url"));
					JSONObject volume = (JSONObject) iterator.next().get("volume");
					comic.setVolume((String) volume.get("name"));
					comic.setDescription((String) iterator.next().get("description"));
					// Publisher subrequest
					String APIPublisherRequest = "https://comicvine.gamespot.com/api/volumes/?api_key=f6929d31c63612dd656e42295cc122010ac74c1c&format=json&filter=id:"
							+ volume.get("id") + "&field_list=publisher";
					HttpRequest publisherRequest = HttpRequest.newBuilder().uri(URI.create(APIPublisherRequest))
							.build();
					HttpResponse<String> publisherResponse = client.send(publisherRequest,
							HttpResponse.BodyHandlers.ofString());
					JSONParser publisherParser = new JSONParser();
					JSONObject publisherjsonObject = (JSONObject) publisherParser.parse(publisherResponse.body());
					JSONArray publisherResults = (JSONArray) publisherjsonObject.get("results");
					JSONObject publisher = (JSONObject) ((JSONObject) publisherResults.get(0)).get("publisher");
					comic.setPublisher((String) publisher.get("name"));
					// End of publisher subrequest
					list.add(comic);
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