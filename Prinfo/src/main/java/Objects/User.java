package Objects;

public class User {

	public static String login;
	public static boolean premium;

	public User(String l, boolean p) {
		login = l;
		premium = p;
	}

	public static boolean isPremium() {
		return premium;
	}

	public static void setPremium(boolean p) {
		premium = p;
	}

	public static String getLogin() {
		return login;
	}

	public static void setLogin(String l) {
		login = l;
	}

}
