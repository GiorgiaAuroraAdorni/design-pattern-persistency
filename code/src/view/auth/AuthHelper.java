package view.auth;

import model.User;

public class AuthHelper {

	private User user;
	
	public AuthHelper(User user) {
		this.user = user;
	}
	
	public boolean isAuthenticated() {
		return (this.user != null);
	}
	
	public boolean isAdmin() {	
		return (this.isAuthenticated());
	}
	
	public String getUsername() {		
		return (this.isAuthenticated()) ? this.user.getUsername() : null;
	}
}
