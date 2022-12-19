package Objects;

public class Comic {
	private String name;
	private String date;
	private String image;
	private String volume;
	private String publisher;
	
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setPublisher(String publisher) {
		this.publisher=publisher;
	}
	public String getPublisher() {
		return publisher;
	}
}
