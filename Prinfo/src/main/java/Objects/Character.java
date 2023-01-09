package Objects;

/**
 * Classe permettant de stocker les informations à propos d'un personnage
 *
 */
public class Character {
	private String name;
	private String publisher;
	private String image;
	private String description;

	/**
	 * Permet de connaitre le nom du personnage
	 * 
	 * @return String name : String contenant le nom du personnage
	 */
	public String getName() {
		return (this.name);
	}

	/**
	 * Permet de définir le nom du personnage
	 * 
	 * @param name : String contenant le nom du personnage
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Permet de connaitre le nom du publisher
	 * 
	 * @return String publisher : String contenant le nom du publisher
	 */
	public String getPublisher() {
		return (this.publisher);
	}

	/**
	 * Permet de définir le nom du publisher
	 * 
	 * @param publisher : String contenant le nom du publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Permet de connaitre l'URL menant vers l'image du personnage
	 * 
	 * @return String image : String contenant l'URL de l'image du personnage
	 */
	public String getImage() {
		return (this.image);
	}

	/**
	 * Permet de définir l'URL menant vers l'image du personnage
	 * 
	 * @param image : String contenant l'URL de l'image du personnage
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Permet de définir la description du personnage
	 * 
	 * @param description : String contenant la description du personnage
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Permet de connaitre la description du personnage
	 * 
	 * @return String description : String contenant la description du personnage
	 */
	public String getDescription() {
		return (this.description);
	}
}