package controller.accesscontrol;

import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.accesscontrol.*;

/**
 * Daitqhe182481
 *
 * @author Zeldais
 */
public abstract class BaseRBACController extends BaseRequiredAuthenticationController {

    private boolean isAuthorized(HttpServletRequest req, User loggeduser) {
        
    }

    protected abstract void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException;

    protected abstract void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException {
        if (isAuthorized(req, loggeduser)) {
            doAuthorizedGet(req, resp, loggeduser);
        } else {
            resp.sendError(403, "you do not have right to access this feature!");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException {
        if (isAuthorized(req, loggeduser)) {
            doAuthorizedPost(req, resp, loggeduser);
        } else {
            resp.sendError(403, "you do not have right to access this feature!");
        }
    }

}
