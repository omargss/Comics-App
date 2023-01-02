package Objects;

import java.util.List;

public class User {
	
	private String login;
	private boolean premium;
	List<Comic> comicList;
	
	public User(String l, boolean p) {
		login = l;
		premium = p;
	}
	public boolean isPremium() {
		return premium;
	}
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void addComic(Comic c) {
		comicList.add(c);
	}
}
