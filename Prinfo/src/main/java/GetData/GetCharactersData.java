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
				+ "&format=json&field_list=name,publisher,image&limit=null";
		String title_formatted = format(name); // permet de formatter le mot afin qu'il soit compréhensible par l'API
												// pour la recherche
		APIRequest += "&filter=name:" + title_formatted;
		System.out.println(APIRequest);

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIRequest)).build(); // Création de la requete
		HttpResponse<String> response;
		List<Character> list = new ArrayList<Character>();

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.body());
			JSONArray results = (JSONArray) jsonObject.get("results"); // Permet de récupérer les résultats de la
																		// requete auprès de l'API

			Iterator<JSONObject> iterator = results.iterator(); // Création d'un itérateur pour parcourir le JSON

			while (iterator.hasNext()) { // Tant qu'on trouve un résultat
				try {
					Character character = new Character(); // Création d'un personnage
					character.setName((String) iterator.next().get("name")); // On récupère le nom du personnage
					//System.out.println(character.getName());

					/*
					 * Erreur : java.util.NoSuchElementException dès qu'on utilise des JSONObject
					 * pourtant, si on va sur la requete, on observe bien que publisher et image
					 * sont des JSONObject et on doit pouvoir les parcourir, au même titre que
					 * iterator
					 */
					
					// JSONObject publisher = (JSONObject) iterator.next().get("publisher"); // On
					// récupère le publisher
					// character.setPublisher((String) publisher.get("name"));
					// JSONObject image = (JSONObject) iterator.next().get("image"); // On récupère
					// l'URL de l'image
					// character.setImage((String) image.get("original_url"));

					list.add(character); // On ajoute le comic à la liste
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
		String textFormatted = text.replace(' ', ',').replace("%", "%25").replace("'", "%27").replace("?", "%3F")
				.replace("!", "%21").replace(":", "%3A").replace(",", "%2C").replace("&", "%26");
		return (textFormatted);
	}
}