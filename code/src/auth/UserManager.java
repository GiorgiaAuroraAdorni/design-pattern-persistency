package auth;

import exception.ExistingUserException;
import model.Address;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserManager {
	
	/** Singleton **/
	private UserManager() {}
	
	private static UserManager sharedManager = null;
	
	public static UserManager getShared() {
		if (sharedManager == null) {
			sharedManager = new UserManager();
		}
		
		return sharedManager;
	}

	private List<Address> addresses = new ArrayList<Address> (Arrays.<Address>asList(
			new Address ("via lambertenghi 1"),
			new Address ("via del picchio 1")));

	private List<User> users = new ArrayList<User> (Arrays.<User>asList(
			new User ("pincopallino", "Pinco Pallino", "pincopallino@usi.ch", addresses.get(1), "pallinopassword", null),
			new User ("gadorni", "Giorgia Adorni", "adorng@usi.ch", addresses.get(0), "gadornipassword",null)));

	public void createUser(User user) throws ExistingUserException {
		if (users.stream().anyMatch(usr -> usr.equals(user))) {
			throw new ExistingUserException();
		}
		
		users.add(user);
	}
	
	public List<User> getUsers() {

		return users;
	}
}
