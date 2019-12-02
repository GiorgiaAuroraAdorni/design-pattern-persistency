package controller.commands.auth;

import controller.commands.AbstractCommand;
import model.Address;
import model.Database;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateCommand extends AbstractCommand {

    @Override
    public void processGet() throws IOException, ServletException {
        String username = request.getParameter("username");

        User user = User.findByUsername(username, Database.getInstance());

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Address address = user.getAddress();

        request.setAttribute("user", user);
        request.setAttribute("address", address);

        sc.getRequestDispatcher("/register/index.jsp").forward(request, response);
    }

    @Override
    public void processPost() throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String bestFriend = request.getParameter("bestFriend");

        User user = User.findByUsername(username, Database.getInstance());

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setBestFriend(bestFriend);

        Address a = new Address(address);
        user.setAddress(a);

        try {
            User.update(user, Database.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/FrontController?command=auth.UserList");
    }

}
