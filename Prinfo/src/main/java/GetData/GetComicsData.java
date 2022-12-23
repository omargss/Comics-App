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

	/**
	 * Permet de récupérer la liste des comics, lorsqu'on effectue la recherche par
	 * le nom du comic
	 * 
	 * @param title   : String contenant le terme de la recherche
	 * @param sort    : String permettant de savoir sur quoi on trie
	 * @param limit   : String contenant un nombre permettant de déterminer le
	 *                nombre limite de comics que la fonction retournera dans la
	 *                liste
	 * @param yearMin : String représentant une année minimale pour afficher un
	 *                comic
	 * @param yearMax : String représentant une année maximale pour afficher un
	 *                comic
	 * @return
	 */
	public static List<Comic> getComicsDataByName(String title, String sort, String limit, String yearMin,
			String yearMax) {
		HttpClient client = HttpClient.newHttpClient();
		String APIRequest = "https://comicvine.gamespot.com/api/issues/?api_key=" + apiKey
				+ "&format=json&field_list=name,cover_date,image,volume&sort=" + sort + "&limit=" + limit;
		String title_formatted = format(title); // permet de formatter le mot afin qu'il soit compréhensible par l'API
												// pour la recherche
		APIRequest += "&filter=name:" + title_formatted;
		APIRequest += ",cover_date:" + yearMin + "-01-01%7C" + yearMax + "-12-31";

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIRequest)).build(); // Création de la requete
		HttpResponse<String> response;
		List<Comic> list = new ArrayList<Comic>();

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.body());
			JSONArray results = (JSONArray) jsonObject.get("results"); // Permet de récupérer les résultats de la
																		// requete auprès de l'API

			Iterator<JSONObject> iterator = results.iterator(); // Création d'un itérateur pour parcourir le JSON

			while (iterator.hasNext()) { // Tant qu'on trouve un résultat
				try {
					Comic comic = new Comic(); // Création d'un comic
					comic.setDate((String) iterator.next().get("cover_date")); // On récupère la date
					comic.setName((String) iterator.next().get("name")); // On récupère le nom
					JSONObject image = (JSONObject) iterator.next().get("image"); // On récupère l'URL de l'image
					comic.setImage((String) image.get("original_url"));
					JSONObject volume = (JSONObject) iterator.next().get("volume"); // On récupère le volume
					comic.setVolume((String) volume.get("name")); // On récupère le nom du volume

					// Publisher subrequest
					String APIPublisherRequest = "https://comicvine.gamespot.com/api/volumes/?api_key=" + apiKey
							+ "&format=json&filter=id:" + volume.get("id") + "&field_list=publisher";
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
		return list; // On retourne la liste des comics trouvés
	}

	/**
	 * Permet de récupérer la liste des comics, lorsqu'on effectue la recherche par
	 * le nom du publieur
	 * 
	 * @param publisher_name : String contenant le terme de la recherche
	 * @param sort           : String permettant de savoir sur quoi on trie
	 * @param limit          : String contenant un nombre permettant de déterminer
	 *                       le nombre limite de comics que la fonction retournera
	 *                       dans la liste
	 * @param yearMin        : String représentant une année minimale pour afficher
	 *                       un comic
	 * @param yearMax        : String représentant une année maximale pour afficher
	 *                       un comic
	 * @return
	 */
	public static List<Comic> getComicsDataByPublisher(String publisher_name, String sort, String limit, String yearMin,
			String yearMax) {
		HttpClient client = HttpClient.newHttpClient();
		String APIRequest = "https://comicvine.gamespot.com/api/issues/?api_key=" + apiKey
				+ "&format=json&field_list=name,cover_date,image,volume&sort=" + sort + "&limit=" + limit;
		String publisher_formatted = format(publisher_name); // permet de formatter le mot afin qu'il soit
																// compréhensible par l'API pour la recherche
		APIRequest += "&filter=publisher.name:" + publisher_formatted;
		APIRequest += ",cover_date:" + yearMin + "-01-01%7C" + yearMax + "-12-31";
		System.out.println(APIRequest);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIRequest)).build();
		HttpResponse<String> response;
		List<Comic> list = new ArrayList<Comic>();

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.body());
			JSONArray results = (JSONArray) jsonObject.get("results");

			Iterator<JSONObject> iterator = results.iterator(); // Création d'un itérateur pour parcourir le JSON

			while (iterator.hasNext()) { // Tant qu'on trouve un résultat
				try {
					Comic comic = new Comic(); // On crée un nouveau comic
					comic.setDate((String) iterator.next().get("cover_date")); // On récupère la date
					comic.setName((String) iterator.next().get("name")); // On récupère le nom
					JSONObject image = (JSONObject) iterator.next().get("image"); // On récupère l'URL de l'image
					comic.setImage((String) image.get("original_url"));
					JSONObject volume = (JSONObject) iterator.next().get("volume"); // On récupère le volume
					comic.setVolume((String) volume.get("name")); // On récupère le nom du volume

					// Publisher subrequest
					String APIPublisherRequest = "https://comicvine.gamespot.com/api/volumes/?api_key=" + apiKey
							+ "&format=json&filter=id:" + volume.get("id") + "&field_list=publisher";
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
		return list; // On retourne la liste des comics trouvés
	}
	
	/**
	 * Permet de formatter un texte pour qu'il soit compréhensible par l'API lors
	 * d'une recherche
	 * 
	 * @param text : String à formatter
	 * @return textFormatted : String formatté pour la recherche via l'API
	 */
	public static String format(String text) {
		String textFormatted = text.replace(' ', ',').replace("%", "%25").replace("'", "%27").replace("?", "%3F")
				.replace("!", "%21").replace(":", "%3A").replace(",", "%2C").replace("&", "%26");
		return (textFormatted);
	}
}