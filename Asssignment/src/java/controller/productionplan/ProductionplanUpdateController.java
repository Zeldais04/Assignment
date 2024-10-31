package controller.productionplan;

import dal.DepartmentDBContext;
import dal.PlanCampaignDBContext;
import dal.PlanDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.Department;
import model.Plan;
import model.PlanCampain;
import model.Product;

/**
 *
 * @author Zeldais
 */
public class ProductionplanUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planIdParam = request.getParameter("planId");
        if (planIdParam == null || planIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Plan ID is missing");
            return;
        }

        try {
            int planId = Integer.parseInt(planIdParam);

            // Truy vấn kế hoạch và các chiến dịch hiện tại
            PlanDBContext planDb = new PlanDBContext();
            Plan plan = planDb.get(planId);
            PlanCampaignDBContext campaignDb = new PlanCampaignDBContext();
            ArrayList<PlanCampain> campaigns = campaignDb.list(planId);

            // Truy vấn danh sách phòng ban (Departments)
            DepartmentDBContext departmentDb = new DepartmentDBContext();
            ArrayList<Department> departments = departmentDb.list();

            // Truy vấn danh sách sản phẩm (Products)
            ProductDBContext productDb = new ProductDBContext();
            ArrayList<Product> products = productDb.list();

            // Truyền dữ liệu vào request
            request.setAttribute("plan", plan);
            request.setAttribute("campaigns", campaigns);
            request.setAttribute("depts", departments);
            request.setAttribute("products", products);
            request.getRequestDispatcher("/view/productionplan/update.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Plan ID format");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planIdParam = request.getParameter("planId");

        if (planIdParam == null || planIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Plan ID is missing");
            return;
        }

        try {
            int planId = Integer.parseInt(planIdParam);
            String[] pids = request.getParameterValues("pid");

            Plan plan = new Plan();
            plan.setId(planId); // Giữ nguyên ID kế hoạch
            plan.setStartTime(Date.valueOf(request.getParameter("from")));
            plan.setEndTime(Date.valueOf(request.getParameter("to")));

            Department d = new Department();
            d.setId(Integer.parseInt(request.getParameter("did")));
            plan.setD(d);

            plan.setCampains(new ArrayList<>());

            for (String pid : pids) {
                Product p = new Product();
                p.setId(Integer.parseInt(pid));

                PlanCampain c = new PlanCampain();
                c.setP(p);
                String raw_quantity = request.getParameter("quantity" + pid);
                String raw_effort = request.getParameter("effort" + pid);
                c.setQuantity(raw_quantity != null && raw_quantity.length() > 0 ? Integer.parseInt(raw_quantity) : 0);
                c.setEffort(raw_effort != null && raw_effort.length() > 0 ? Float.parseFloat(raw_effort) : 0);
                c.setPl(plan);
                if (c.getQuantity() != 0 && c.getEffort() != 0) {
                    plan.getCampains().add(c);
                }
            }

            if (!plan.getCampains().isEmpty()) {
                PlanDBContext db = new PlanDBContext();
                db.update(plan); // Thực hiện cập nhật kế hoạch
                response.getWriter().println("Updated the plan!");
            } else {
                response.getWriter().println("Your plan did not have any campains");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
        }
    }
}
