package controller.commands.auth;

import auth.UserManager;
import controller.commands.AbstractCommand;
import exception.ExistingUserException;
import model.Address;
import model.User;

import java.io.IOException;

public class RegisterCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void processPost() throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String bestFriend = request.getParameter("bestFriend");

		Address a = new Address(address);
		User bf = new User(bestFriend, bestFriend, null, null, null, null);

		try {
			UserManager.getShared().createUser(new User(username, name, email, a, password, bf));
		} catch (ExistingUserException e) {
			throw new RuntimeException(e);
		}
		
		response.sendRedirect("/design_pattern/");
	}
}
