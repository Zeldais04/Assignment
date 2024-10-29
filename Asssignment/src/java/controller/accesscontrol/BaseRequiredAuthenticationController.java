package controller.accesscontrol;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.accesscontrol.User;

/**
 * Daitqhe182481
 *
 * @author Zeldais
 */
public abstract class BaseRequiredAuthenticationController extends HttpServlet {

    private boolean isAuthenticated(HttpServletRequest req) {
        return req.getSession().getAttribute("account") != null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            //do business logic
            doPost(req, resp, (User) req.getSession().getAttribute("account"));
        } else {
            resp.sendError(403, "You do not have right to access this page.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            //do business logic
            doGet(req, resp, (User) req.getSession().getAttribute("account"));
        } else {
            resp.sendError(403, "You do not have right to access this page.");
        }
    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException;

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException;
}
