package Objects;

/**
 * Classe permettant de stocker les informations à propos d'un publisher
 *
 */
public class Publisher {
	private String name;
	private String image;
	private String description;

	/**
	 * Permet de connaitre le nom du publisher
	 * 
	 * @return String name : String contenant le nom du publisher
	 */
	public String getName() {
		return (this.name);
	}

	/**
	 * Permet de définir le nom du publisher
	 * 
	 * @param name : String contenant le nom du publisher
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Permet de connaitre l'URL menant vers l'image du publisher
	 * 
	 * @return String image : String contenant l'URL de l'image du publisher
	 */
	public String getImage() {
		return (this.image);
	}

	/**
	 * Permet de définir l'URL menant vers l'image du publisher
	 * 
	 * @param image : String contenant l'URL de l'image du publisher
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Permet de définir la description du publisher
	 * 
	 * @param description : String contenant la description du publisher
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Permet de connaitre la description du publisher
	 * 
	 * @return String description : String contenant la description du publisher
	 */
	public String getDescription() {
		return (this.description);
	}
}