package Objects;

public class User {
	
	private String login;
	private boolean premium;
	
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

}
