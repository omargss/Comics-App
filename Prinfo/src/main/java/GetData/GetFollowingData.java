package GetData;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Objects.Comic;

public class GetFollowingData {
	private static String apiKey = "f6929d31c63612dd656e42295cc122010ac74c1c";

	public static Comic previousComic(long idVolume, long issueNumber) throws ParseException {
		List<Comic> list = new ArrayList<Comic>();
		HttpClient client = HttpClient.newHttpClient();
		String APIRequest = "https://comicvine.gamespot.com/api/volume/4050-" + idVolume + "/?api_key=" + apiKey
				+ "&format=json&field_list=issues";
		// System.out.println(APIRequest);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIRequest)).build(); // Création de la requete
		HttpResponse<String> response;

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.body());
			// System.out.println(jsonObject);

			JSONObject issues = (JSONObject) jsonObject.get("results");
			// System.out.println(issues);
			JSONArray results = (JSONArray) issues.get("issues");
			// System.out.println(results);

			Iterator<JSONObject> iterator = results.iterator();

			// Permet de récupérer les résultats de la
			// requete auprès de l'API

			// Création d'un itérateur pour parcourir le JSON
			while (iterator.hasNext()) { // Tant qu'on trouve un résultat
				Comic comic = new Comic(); // Création d'un comic
				JSONObject tempComic = iterator.next(); // Récupération du contenu de l'iterator
				comic.setIssue((long) tempComic.get("id"));
				comic.setIssueNumber((long) Long.parseLong((String) tempComic.get("issue_number")));
				list.add(comic); // On ajoute le comic à la liste
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean test = false;
		//Comic comicReturn = new Comic();

		for (int i = 0; i < list.size(); i++) {
			long idIssue = list.get(i).getIssueNumber();
			if (idIssue == issueNumber - 1) {
				Comic comic = getComicFromId(list.get(i).getIssue());
				test = true;
				//comicReturn = getComicFromId(list.get(i).getIssue());
				return (comic);
			}
		}
		if (test) {
			System.out.println("Comic trouvé");
		} else {
			System.out.println("Comic non trouvé");
		}
		return (null);

	}

	public static Comic nextComic(long idVolume, long issueNumber) throws ParseException {
		List<Comic> list = new ArrayList<Comic>();
		HttpClient client = HttpClient.newHttpClient();
		String APIRequest = "https://comicvine.gamespot.com/api/volume/4050-" + idVolume + "/?api_key=" + apiKey
				+ "&format=json&field_list=issues";
		//System.out.println(APIRequest);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIRequest)).build(); // Création de la requete
		HttpResponse<String> response;

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.body());
			JSONObject issues = (JSONObject) jsonObject.get("results");
			JSONArray results = (JSONArray) issues.get("issues");

			Iterator<JSONObject> iterator = results.iterator();

			// Permet de récupérer les résultats de la
			// requete auprès de l'API

			// Création d'un itérateur pour parcourir le JSON
			while (iterator.hasNext()) { // Tant qu'on trouve un résultat
				Comic comic = new Comic(); // Création d'un comic
				JSONObject tempComic = iterator.next(); // Récupération du contenu de l'iterator
				comic.setIssue((long) tempComic.get("id"));
				comic.setIssueNumber((long) Long.parseLong((String) tempComic.get("issue_number")));
				list.add(comic); // On ajoute le comic à la liste
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean test = false;
		//Comic comicReturn = new Comic();

		for (int i = 0; i < list.size(); i++) {
			long idIssue = list.get(i).getIssueNumber();
			if (idIssue == issueNumber + 1) {
				Comic comic = getComicFromId(list.get(i).getIssue());
				test = true;
				//comicReturn = getComicFromId(list.get(i).getIssue());
				return (comic);
			}
		}
		if (test) {
			System.out.println("Comic trouvé");
		} else {
			System.out.println("Comic non trouvé");
		}
		return (null);
	}

	public static Comic getComicFromId(long id) throws ParseException {
		Comic comic = new Comic();
		HttpClient client = HttpClient.newHttpClient();
		String APIRequest = "https://comicvine.gamespot.com/api/issue/4000-" + id + "/?api_key=" + apiKey
				+ "&format=json";
		// System.out.println(APIRequest);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIRequest)).build(); // Création de la requete
		HttpResponse<String> response;

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.body());
			JSONObject results = (JSONObject) jsonObject.get("results");

			comic.setDate((String) results.get("cover_date")); // On récupère la date
			comic.setName((String) results.get("name")); // On récupère le nom
			//System.out.println(results.get("name"));
			JSONObject image = (JSONObject) results.get("image"); // On récupère l'URL de l'image
			comic.setImage((String) image.get("original_url"));
			JSONObject volume = (JSONObject) results.get("volume"); // On récupère le volume
			comic.setVolume((String) volume.get("name")); // On récupère le nom du volume
			comic.setDescription((String) results.get("description"));
			comic.setIssue((long) results.get("id"));
			comic.setIdVolume((long) volume.get("id"));
			comic.setIssueNumber((long) Long.parseLong((String) results.get("issue_number")));
			//System.out.println("Issue number : " + results.get("issue_number"));

			// Publisher subrequest
			String APIPublisherRequest = "https://comicvine.gamespot.com/api/volumes/?api_key=" + apiKey
					+ "&format=json&filter=id:" + volume.get("id") + "&field_list=publisher";
			// System.out.println(APIPublisherRequest);
			HttpRequest publisherRequest = HttpRequest.newBuilder().uri(URI.create(APIPublisherRequest)).build();
			HttpResponse<String> publisherResponse = client.send(publisherRequest,
					HttpResponse.BodyHandlers.ofString());
			JSONParser publisherParser = new JSONParser();
			JSONObject publisherjsonObject = (JSONObject) publisherParser.parse(publisherResponse.body());
			JSONArray publisherResults = (JSONArray) publisherjsonObject.get("results");
			JSONObject publisher = (JSONObject) ((JSONObject) publisherResults.get(0)).get("publisher");
			comic.setPublisher((String) publisher.get("name")); // On récupère le publieur
			// End of publisher subrequest

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (comic);
	}
}
