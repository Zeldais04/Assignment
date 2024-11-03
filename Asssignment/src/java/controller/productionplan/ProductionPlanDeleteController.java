package controller.productionplan;

import controller.accesscontrol.BaseRBACController;
import dal.PlanDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.accesscontrol.User;

/**
 *
 * @author Zeldais
 */
public class ProductionPlanDeleteController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        String planIdParam = request.getParameter("planId");
        if (planIdParam == null || planIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Plan ID is missing");
            return;
        }

        try {
            int planId = Integer.parseInt(planIdParam);
            PlanDBContext planDb = new PlanDBContext();
            planDb.delete(planId); // Thực hiện xóa kế hoạch theo `planId`

            // Điều hướng về trang danh sách kế hoạch sản xuất sau khi xóa thành công
            response.sendRedirect(request.getContextPath() + "/productionplan/list");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Plan ID format");
        }
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
