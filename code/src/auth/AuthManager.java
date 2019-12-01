package auth;

import model.Database;
import model.User;

import javax.servlet.http.HttpSession;

public class AuthManager {

	private static String LOGGED_USER_KEY = "loggedUser";

	private HttpSession session;

	private Database db = Database.getInstance();
	
	public AuthManager(HttpSession session) {
		this.session = session;
	}
	
	public User getUser() {
		return (User) session.getAttribute(LOGGED_USER_KEY);
	}
	
	public boolean login(String username, String password) {
		User selectedUser = User.findByUsername(username, db);
		
		if (selectedUser == null || !selectedUser.getPassword().equals(password)) {
			return false;
		}

		session.setAttribute(LOGGED_USER_KEY, selectedUser);
		return true;
	}
	
	public void logout() {
		session.removeAttribute(LOGGED_USER_KEY);
	}

}
