package GetData;

import Objects.Character;
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

	/**
	 * Permet de récupérer une liste de personnages en recherchant via un nom
	 * 
	 * @param name : terme de la recherche
	 * @return List<Character> : liste de personnages satisfaisant la recherche
	 */
	public static List<Character> getCharacters(String name) {
		HttpClient client = HttpClient.newHttpClient();
		String APIRequest = "https://comicvine.gamespot.com/api/characters/?api_key=" + apiKey
				+ "&format=json&field_list=name,publisher,image,description&limit=null";
		String title_formatted = format(name); // permet de formatter le mot afin qu'il soit compréhensible par l'API
												// pour la recherche
		APIRequest += "&filter=name:" + title_formatted;
		// System.out.println(APIRequest);

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIRequest)).build(); // Création de la requete
		HttpResponse<String> response;
		List<Character> list = new ArrayList<Character>();

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
					JSONObject tempCharacter = iterator.next();
					// System.out.println(tempCharacter);
					JSONObject tempPublisherCharacter = (JSONObject) tempCharacter.get("publisher");
					// System.out.println(tempPublisherCharacter);
					JSONObject tempImageCharacter = (JSONObject) tempCharacter.get("image");

					Character character = new Character(); // Création d'un personnage qu'on ajoutera à la liste

					// MàJ des informations du personnage
					character.setName((String) tempCharacter.get("name")); // On récupère le nom du personnage
					character.setImage((String) tempImageCharacter.get("original_url"));
					character.setDescription((String) tempCharacter.get("description"));
					if (tempPublisherCharacter == null) {
						character.setPublisher("");
					} else {
						character.setPublisher((String) tempPublisherCharacter.get("name"));
					}

					// Verifications
					/*
					 * System.out.println(tempCharacter);
					 * System.out.println("Nom :"+character.getName());
					 * System.out.println("Publisher :"+character.getPublisher());
					 * System.out.println("Image :"+character.getImage());
					 */

					list.add(character); // On ajoute le personnage à la liste
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

	/**
	 * Permet de formatter un texte pour qu'il soit compréhensible par l'API lors
	 * d'une recherche
	 * 
	 * @param text : String à formatter
	 * @return textFormatted : String formatté pour la recherche via l'API
	 */
	public static String format(String text) {
		String textFormatted = text.replace("%", "%25").replace("'", "%27").replace("?", "%3F")
				.replace("!", "%21").replace(":", "%3A").replace(",", "%2C").replace("&", "%26").replace(" ", "+")
				.replace("@", "%60").replace("#", "%23").replace("/", "%2F");
		return (textFormatted);
	}
}