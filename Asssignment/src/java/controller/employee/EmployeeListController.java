package controller.employee;

import dal.EmployeeDBcontext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Employee;
import controller.accesscontrol.BaseRBACController;
import model.accesscontrol.User;

/**
 *
 * @author Zeldais
 */
public class EmployeeListController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {

        EmployeeDBcontext db = new EmployeeDBcontext();
        ArrayList<Employee> employees = db.list();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/view/employee/list.jsp").forward(request, response);
    
}

@Override
protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
