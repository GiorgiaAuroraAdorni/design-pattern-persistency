package controller.commands.auth;

import controller.commands.AbstractCommand;
import model.Database;
import model.User;

import java.io.IOException;

public class DeleteCommand extends AbstractCommand {

    @Override
    public void processGet() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void processPost() throws IOException {
        String username = request.getParameter("username");
        User user = User.findByUsername(username, Database.getInstance());

        try {
            User.delete(user, Database.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/FrontController?command=auth.UserList");
    }
}
