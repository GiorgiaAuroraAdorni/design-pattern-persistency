package controller.commands.auth;

import auth.AuthManager;
import controller.commands.AbstractCommand;
import model.Database;

import java.io.IOException;

public class LoginCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void processPost() throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		AuthManager auth = new AuthManager(request.getSession());
		
		if (!auth.login(username, password)) {
			throw new RuntimeException("Login Failed");
		}
		
		response.sendRedirect("/design_pattern/FrontController?command=auth.UserList");
	}

	Database db = Database.getInstance();
}
