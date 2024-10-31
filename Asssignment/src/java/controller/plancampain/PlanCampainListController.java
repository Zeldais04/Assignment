package controller.plancampain;

import dal.PlanCampaignDBContext;
import dal.PlanDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Plan;
import model.PlanCampain;

/**
 *
 * @author Zeldais
 */
public class PlanCampainListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planIdParam = request.getParameter("planId");
        if (planIdParam == null || planIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Plan ID is missing");
            return;
        }

        try {
            int planId = Integer.parseInt(planIdParam);
            PlanCampaignDBContext db = new PlanCampaignDBContext();
            ArrayList<PlanCampain> campaigns = db.list(planId);

            // Truy vấn thông tin kế hoạch sản xuất (Plan)
            PlanDBContext planDb = new PlanDBContext();
            Plan plan = planDb.get(planId);

            request.setAttribute("campaigns", campaigns);
            request.setAttribute("planId", planId);
            request.setAttribute("plan", plan); // Gửi đối tượng kế hoạch sản xuất vào JSP
            request.getRequestDispatcher("/view/plancampain/list.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Plan ID format");
        }
    }
}
