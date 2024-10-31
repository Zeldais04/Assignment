package controller.productionplan;

import dal.PlanCampaignDBContext;
import dal.PlanDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import model.Plan;
import model.PlanCampain;

/**
 *
 * @author Zeldais
 */
public class ProductionplanUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chuyển hướng về trang chỉnh sửa nếu truy cập bằng GET
        response.sendRedirect(request.getContextPath() + "/view/productionplan/update.jsp?planId=" + request.getParameter("planId"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Logic xử lý cập nhật
        int planId = Integer.parseInt(request.getParameter("planId"));
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate = Date.valueOf(request.getParameter("endDate"));

        PlanDBContext planDb = new PlanDBContext();
        Plan plan = planDb.get(planId);
        if (plan != null) {
            plan.setStartTime(startDate);
            plan.setEndTime(endDate);
            planDb.update(plan);
        }

        // Cập nhật thông tin các chiến dịch
        PlanCampaignDBContext campaignDb = new PlanCampaignDBContext();
        String[] campaignIds = request.getParameterValues("campaignId");
        if (campaignIds != null) {
            for (String campaignIdStr : campaignIds) {
                int campaignId = Integer.parseInt(campaignIdStr);
                int quantity = Integer.parseInt(request.getParameter("quantity_" + campaignId));
                float effort = Float.parseFloat(request.getParameter("effort_" + campaignId));

                PlanCampain campaign = campaignDb.get(campaignId);
                if (campaign != null) {
                    campaign.setQuantity(quantity);
                    campaign.setEffort(effort);
                    campaignDb.update(campaign);
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/plancampain/list.jsp?planId=" + planId);
    }
}
