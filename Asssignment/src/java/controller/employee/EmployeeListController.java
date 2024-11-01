package controller.employee;



import dal.EmployeeDBcontext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Employee;

/**
 *
 * @author Zeldais
 */
public class EmployeeListController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDBcontext db = new EmployeeDBcontext();
        ArrayList<Employee> employees = db.list();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/view/employee/list.jsp").forward(request, response);
    }

}
