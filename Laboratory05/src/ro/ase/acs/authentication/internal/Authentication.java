package ro.ase.acs.authentication.internal;

public class Authentication {
	public static boolean login(String username, String password) {
		return "admin".equals(username) && "admin".equals(password);
	}
}
