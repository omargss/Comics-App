package Objects;

public class Comic {
	private String name;
	private String date;
	private String image; // Il s'agit d'une URL menant à l'image
	private String volume;
	private String publisher;

	/**
	 * Permet de connaitre le volume du comic
	 * 
	 * @return String volume : String contenant le nom du volume du comic
	 */
	public String getVolume() {
		return this.volume;
	}

	/**
	 * Permet de définir le volume du comic
	 * 
	 * @param volume : String contnenant le nom du volume du comic
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}

	/**
	 * Permet de connaitre le nom du comic
	 * 
	 * @return String name : String contenant le nom du comic
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Permet de définir le nom du comic
	 * 
	 * @param name : String contenant le nom du comic
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Permet de connaitre la date du comic
	 * 
	 * @return String date : String contenant la date du comic
	 */
	public String getDate() {
		return this.date;
	}

	/**
	 * Permet de définir la date du comic
	 * 
	 * @param date : String contenant la date du comic
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Permet de connaitre l'URL contenant l'image du comic
	 * 
	 * @return image : String contenant l'URL de l'image du comic
	 */
	public String getImage() {
		return this.image;
	}

	/**
	 * Permet de définir l'URL contenant l'image du comic
	 * 
	 * @param image : String contenant l'URL de l'image du comic
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Permet de définir le nom du publieur du comic
	 * 
	 * @param publisher : String contenant le nom du publieur du comic
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Permet de connaitre le nom du publieur du comic
	 * 
	 * @return publisher : String contenant le nom du publieur du comic
	 */
	public String getPublisher() {
		return this.publisher;
	}
}
