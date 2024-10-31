package controller.productionplan;

import dal.PlanDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Plan;

/**
 *
 * @author Zeldais
 */
public class ProductionplanUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDBContext db = new PlanDBContext();
        ArrayList<Plan> plans = db.list();
        request.setAttribute("plans", plans);
        request.getRequestDispatcher("/view/productionplan/list.jsp").forward(request, response);
    }
}
