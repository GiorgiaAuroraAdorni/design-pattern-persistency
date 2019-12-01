package controller.commands.auth;

import controller.commands.AbstractCommand;
import model.Database;
import model.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

public class UserListCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException, ServletException {
		List<User> users = User.findAll(Database.getInstance());
		
		request.setAttribute("users", users);
		
		sc.getRequestDispatcher("/users/index.jsp").forward(request, response);
	}

	@Override
	public void processPost() throws IOException {
		throw new UnsupportedOperationException();	
	}
}
