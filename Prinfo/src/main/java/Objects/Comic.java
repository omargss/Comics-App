package Objects;

import java.util.Comparator;

public class Comic {
	private String name;
	private String url;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	// Méthode pour créer des comparator et trier selon le bon champ de la classe
	public static Comparator<Comic> NameComparator = new Comparator<Comic>() {

        @Override
        public int compare(Comic e1, Comic e2) {
            return e1.getName().compareTo(e2.getName());
        }
    };
    public static Comparator<Comic> NameComparatordec = new Comparator<Comic>() {

        @Override
        public int compare(Comic e1, Comic e2) {
            return e2.getName().compareTo(e1.getName());
        }
    };
    public static Comparator<Comic> DateComparator = new Comparator<Comic>() {

        @Override
        public int compare(Comic e1, Comic e2) {
            return e1.getDate().compareTo(e2.getDate());
        }
    };
    public static Comparator<Comic> DateComparatordec = new Comparator<Comic>() {

        @Override
        public int compare(Comic e1, Comic e2) {
            return e2.getDate().compareTo(e1.getDate());
        }
    };
    public static Comparator<Comic> PublisherComparator = new Comparator<Comic>() {

        @Override
        public int compare(Comic e1, Comic e2) {
            return e1.getPublisher().compareTo(e2.getPublisher());
        }
    };
    public static Comparator<Comic> PublisherComparatordec = new Comparator<Comic>() {

        @Override
        public int compare(Comic e1, Comic e2) {
            return e2.getPublisher().compareTo(e1.getPublisher());
        }
    };
}
