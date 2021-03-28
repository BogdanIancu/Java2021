package ro.ase.acs.authentication;

import java.security.InvalidAlgorithmParameterException;

import ro.ase.acs.authentication.internal.Authentication;

public class User {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void login() throws InvalidAlgorithmParameterException {
		if(username == null || password == null) {
			throw new InvalidAlgorithmParameterException();
		}
		if(Authentication.login(username, password)) {
			System.out.println("Successfully authenticated!");
		} else {
			System.out.println("Invalid credentials!");
		}
	}
}
