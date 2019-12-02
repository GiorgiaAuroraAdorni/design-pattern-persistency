package controller.commands.auth;

import controller.commands.AbstractCommand;
import model.Address;
import model.Database;
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

		try {
			User.create(new User(username, name, email, a, password, bestFriend), Database.getInstance());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		response.sendRedirect("/design_pattern/FrontController?command=auth.UserList");
	}
}
