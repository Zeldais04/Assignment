package controller.productionplan;

import dal.PlanCampaignDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.PlanCampain;

/**
 *
 * @author Zeldais
 */
public class PlanCampainListController extends HttpServlet {

   

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int planId = Integer.parseInt(request.getParameter("planId"));
            PlanCampaignDBContext db = new PlanCampaignDBContext();
            ArrayList<PlanCampain> campaigns = db.list(planId);
            request.setAttribute("campaigns", campaigns);
            request.setAttribute("planId", planId);
            request.getRequestDispatcher("/view/plancampain/list.jsp").forward(request, response);
        }
    
}
