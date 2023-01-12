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

	public static Comic previousComic(long idVolume, long idComic) throws ParseException {
		List<Comic> list = new ArrayList<Comic>();
		HttpClient client = HttpClient.newHttpClient();
		String APIRequest = "https://comicvine.gamespot.com/api/volume/4050-" + idVolume + "/?api_key=" + apiKey
				+ "&format=json&field_list=issues";

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIRequest)).build(); // Création de la requete
		HttpResponse<String> response;

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.body());
			JSONArray results = (JSONArray) jsonObject.get("results");

			Iterator<JSONObject> iterator = results.iterator();

			try {

				// Permet de récupérer les résultats de la
				// requete auprès de l'API

				// Création d'un itérateur pour parcourir le JSON
				while (iterator.hasNext()) { // Tant qu'on trouve un résultat
					try {
						Comic comic = new Comic(); // Création d'un comic
						JSONObject tempComic = iterator.next(); // Récupération du contenu de l'iterator
						comic.setDate((String) tempComic.get("cover_date")); // On récupère la date
						comic.setName((String) tempComic.get("name")); // On récupère le nom
						JSONObject image = (JSONObject) tempComic.get("image"); // On récupère l'URL de l'image
						comic.setImage((String) image.get("original_url"));
						JSONObject volume = (JSONObject) tempComic.get("volume"); // On récupère le volume
						comic.setVolume((String) volume.get("name")); // On récupère le nom du volume
						comic.setDescription((String) tempComic.get("description"));
						comic.setIssue((long) tempComic.get("id"));

						// Publisher subrequest
						String APIPublisherRequest = "https://comicvine.gamespot.com/api/volumes/?api_key=" + apiKey
								+ "&format=json&filter=id:" + volume.get("id") + "&field_list=publisher";
						System.out.println(APIPublisherRequest);
						HttpRequest publisherRequest = HttpRequest.newBuilder().uri(URI.create(APIPublisherRequest))
								.build();
						HttpResponse<String> publisherResponse = client.send(publisherRequest,
								HttpResponse.BodyHandlers.ofString());
						JSONParser publisherParser = new JSONParser();
						JSONObject publisherjsonObject = (JSONObject) publisherParser.parse(publisherResponse.body());
						JSONArray publisherResults = (JSONArray) publisherjsonObject.get("results");
						JSONObject publisher = (JSONObject) ((JSONObject) publisherResults.get(0)).get("publisher");
						comic.setPublisher((String) publisher.get("name")); // On récupère le publieur
						// End of publisher subrequest

						list.add(comic); // On ajoute le comic à la liste
					} catch (NoSuchElementException nsee) {
						nsee.printStackTrace(); // En cas d'erreur, on affiche le problème dans la console
					}
				}
			} catch (IOException e) {
				e.printStackTrace(); // En cas d'erreur, on affiche le problème dans la console
			} catch (InterruptedException e) {
				e.printStackTrace(); // En cas d'erreur, on affiche le problème dans la console
			} catch (ParseException e) {
				e.printStackTrace(); // En cas d'erreur, on affiche le problème dans la console
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getIssue() == idComic & i>0) {
				return (list.get(i - 1));
			}
		}

		return (null);
	}
	
	public static Comic nextComic(long idVolume, long idComic) throws ParseException {
		List<Comic> list = new ArrayList<Comic>();
		HttpClient client = HttpClient.newHttpClient();
		String APIRequest = "https://comicvine.gamespot.com/api/volume/4050-" + idVolume + "/?api_key=" + apiKey
				+ "&format=json&field_list=issues";

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIRequest)).build(); // Création de la requete
		HttpResponse<String> response;

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.body());
			JSONArray results = (JSONArray) jsonObject.get("results");

			Iterator<JSONObject> iterator = results.iterator();

			try {

				// Permet de récupérer les résultats de la
				// requete auprès de l'API

				// Création d'un itérateur pour parcourir le JSON
				while (iterator.hasNext()) { // Tant qu'on trouve un résultat
					try {
						Comic comic = new Comic(); // Création d'un comic
						JSONObject tempComic = iterator.next(); // Récupération du contenu de l'iterator
						comic.setDate((String) tempComic.get("cover_date")); // On récupère la date
						comic.setName((String) tempComic.get("name")); // On récupère le nom
						JSONObject image = (JSONObject) tempComic.get("image"); // On récupère l'URL de l'image
						comic.setImage((String) image.get("original_url"));
						JSONObject volume = (JSONObject) tempComic.get("volume"); // On récupère le volume
						comic.setVolume((String) volume.get("name")); // On récupère le nom du volume
						comic.setDescription((String) tempComic.get("description"));
						comic.setIssue((long) tempComic.get("id"));

						// Publisher subrequest
						String APIPublisherRequest = "https://comicvine.gamespot.com/api/volumes/?api_key=" + apiKey
								+ "&format=json&filter=id:" + volume.get("id") + "&field_list=publisher";
						System.out.println(APIPublisherRequest);
						HttpRequest publisherRequest = HttpRequest.newBuilder().uri(URI.create(APIPublisherRequest))
								.build();
						HttpResponse<String> publisherResponse = client.send(publisherRequest,
								HttpResponse.BodyHandlers.ofString());
						JSONParser publisherParser = new JSONParser();
						JSONObject publisherjsonObject = (JSONObject) publisherParser.parse(publisherResponse.body());
						JSONArray publisherResults = (JSONArray) publisherjsonObject.get("results");
						JSONObject publisher = (JSONObject) ((JSONObject) publisherResults.get(0)).get("publisher");
						comic.setPublisher((String) publisher.get("name")); // On récupère le publieur
						// End of publisher subrequest

						list.add(comic); // On ajoute le comic à la liste
					} catch (NoSuchElementException nsee) {
						nsee.printStackTrace(); // En cas d'erreur, on affiche le problème dans la console
					}
				}
			} catch (IOException e) {
				e.printStackTrace(); // En cas d'erreur, on affiche le problème dans la console
			} catch (InterruptedException e) {
				e.printStackTrace(); // En cas d'erreur, on affiche le problème dans la console
			} catch (ParseException e) {
				e.printStackTrace(); // En cas d'erreur, on affiche le problème dans la console
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getIssue() == idComic & i<list.size()-1) {
				return (list.get(i + 1));
			}
		}

		return (null);
	}
}