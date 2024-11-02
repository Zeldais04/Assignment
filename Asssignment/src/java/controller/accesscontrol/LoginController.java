
package controller.accesscontrol;

import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.accesscontrol.User;

/**
 *
 * @author Zeldais
 */
public class LoginController extends HttpServlet {
   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //
        request.getRequestDispatcher("login.html").forward(request, response);
        //
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        UserDBContext db = new UserDBContext();
        User account = db.get(user, pass);
        
        if(account!=null)
        {
            request.getSession().setAttribute("account", account);
            response.sendRedirect("home.html");
        }
        else
            response.sendRedirect("login.html");
        
        response.getWriter().println(getInitParameter("private"));
    }

    

}
