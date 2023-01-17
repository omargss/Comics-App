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

import Objects.Publisher;

public class GetPublishersData {
	private static String apiKey = "f6929d31c63612dd656e42295cc122010ac74c1c";

	public static List<Publisher> getPublishers(String name) {
		HttpClient client = HttpClient.newHttpClient();
		String APIRequest = "https://comicvine.gamespot.com/api/publishers/?api_key=" + apiKey
				+ "&format=json&field_list=name,image,description&limit=null";
		String publisher_formatted = format(name); // permet de formatter le mot afin qu'il soit compréhensible par
													// l'API
													// pour la recherche
		APIRequest += "&filter=name:" + publisher_formatted;
		// System.out.println(APIRequest);

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIRequest)).build(); // Création de la requete
		HttpResponse<String> response;
		List<Publisher> list = new ArrayList<Publisher>();

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.body());
			JSONArray results = (JSONArray) jsonObject.get("results"); // Permet de récupérer les résultats de la
																		// requete auprès de l'API
			@SuppressWarnings("unchecked") // Using legacy API
			Iterator<JSONObject> iterator = results.iterator(); // Création d'un itérateur pour parcourir le JSON

			while (iterator.hasNext()) { // Tant qu'on trouve un résultat
				try {
					// On récupère la valeur à la pos de l'iterator
					JSONObject tempPublisher = iterator.next();
					// System.out.println(tempPublisherCharacter);
					JSONObject tempImagePublisher = (JSONObject) tempPublisher.get("image");

					Publisher publisher = new Publisher(); // Création d'un personnage qu'on ajoutera à la liste

					// MàJ des informations du personnage
					publisher.setName((String) tempPublisher.get("name")); // On récupère le nom du personnage
					publisher.setImage((String) tempImagePublisher.get("original_url"));
					publisher.setDescription((String) tempPublisher.get("description"));

					// Verifications
					/*
					 * System.out.println(tempCharacter);
					 * System.out.println("Nom :"+character.getName());
					 * System.out.println("Publisher :"+character.getPublisher());
					 * System.out.println("Image :"+character.getImage());
					 */

					list.add(publisher); // On ajoute le personnage à la liste
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
		return (list);
	}

	public static String format(String text) {
		String textFormatted = text.replace("%", "%25").replace("'", "%27").replace("?", "%3F").replace("!", "%21")
				.replace(":", "%3A").replace(",", "%2C").replace("&", "%26").replace(" ", "+").replace("@", "%60")
				.replace("#", "%23").replace("/", "%2F");
		return (textFormatted);
	}
}