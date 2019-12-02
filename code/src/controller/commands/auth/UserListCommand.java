package controller.commands.auth;

import controller.commands.AbstractCommand;
import model.Address;
import model.Database;
import model.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

public class UserListCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException, ServletException {
		String field = request.getParameter("field");
		String query = request.getParameter("query");

		Database db = Database.getInstance();
		List<User> users = User.findAll(Database.getInstance());

		if (field != null && query != null && !query.equals("")) {
			switch (field) {
				case "name":
					users = User.findAllByName(query, db);
					break;

				case "email":
					users = User.findAllByEmail(query, db);
					break;

				case "address":
					users = User.findAllByAddress(query, db);
					break;

				case "best_friend":
					users = User.findAllByBestFriend(query, db);
					break;

				default:
					throw new UnsupportedOperationException();
			}
		}
		request.setAttribute("users", users);

		sc.getRequestDispatcher("/users/index.jsp").forward(request, response);
	}

	@Override
	public void processPost() throws IOException {
		throw new UnsupportedOperationException();
	}
}
