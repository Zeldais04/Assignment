package controller.productionplan;

import controller.accesscontrol.BaseRBACController;
import dal.DepartmentDBContext;
import dal.PlanCampaignDBContext;
import dal.PlanDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.Department;
import model.Plan;
import model.PlanCampain;
import model.Product;
import model.accesscontrol.User;

/**
 *
 * @author Zeldais
 */
public class ProductionplanUpdateController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
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
            ArrayList<Department> departments = departmentDb.get("workshop");

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
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
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

            try {
                // Kiểm tra và lấy giá trị startTime và endTime
                plan.setStartTime(Date.valueOf(request.getParameter("from")));
                plan.setEndTime(Date.valueOf(request.getParameter("to")));

                // Kiểm tra giá trị endTime phải lớn hơn startTime
                if (!plan.getEndTime().after(plan.getStartTime())) {
                    throw new IllegalArgumentException("Thời gian bắt đầu phải trước thời gian kết thúc");
                }
            } catch (IllegalArgumentException e) {
                // Truyền lại các giá trị đã nhập vào request
                request.setAttribute("errorMessage", "Thời gian bắt đầu phải trước thời gian kết thúc");
                request.setAttribute("from", request.getParameter("from"));
                request.setAttribute("to", request.getParameter("to"));
                request.setAttribute("did", request.getParameter("did"));
                request.setAttribute("pids", pids);

                // Truy vấn lại dữ liệu từ DBContext
                PlanDBContext planDb = new PlanDBContext();
                Plan existingPlan = planDb.get(planId);
                request.setAttribute("plan", existingPlan);

                PlanCampaignDBContext campaignDb = new PlanCampaignDBContext();
                ArrayList<PlanCampain> campaigns = campaignDb.list(planId);
                request.setAttribute("campaigns", campaigns);

                DepartmentDBContext departmentDb = new DepartmentDBContext();
                ArrayList<Department> departments = departmentDb.get("workshop");
                request.setAttribute("depts", departments);

                ProductDBContext productDb = new ProductDBContext();
                ArrayList<Product> products = productDb.list();
                request.setAttribute("products", products);

                // Forward lại trang update.jsp với dữ liệu đã nhập và thông báo lỗi
                request.getRequestDispatcher("/view/productionplan/update.jsp").forward(request, response);
                return;
            }

            Department d = new Department();
            d.setId(Integer.parseInt(request.getParameter("did")));
            plan.setD(d);

            plan.setCampains(new ArrayList<>());

            for (String pid : pids) {
                Product p = new Product();
                p.setId(Integer.parseInt(pid));

                PlanCampain c = new PlanCampain();
                c.setP(p);
                try {
                    String raw_quantity = request.getParameter("quantity" + pid);
                    String raw_effort = request.getParameter("effort" + pid);
                    c.setQuantity(raw_quantity != null && raw_quantity.length() > 0 ? Integer.parseInt(raw_quantity) : 0);
                    c.setEffort(raw_effort != null && raw_effort.length() > 0 ? Float.parseFloat(raw_effort) : 0);
                } catch (NumberFormatException e) {
                    // Truyền lại thông báo lỗi và các giá trị đã nhập
                    request.setAttribute("errorMessage", "Số lượng phải là số nguyên và effort phải là số thực");
                    request.setAttribute("from", request.getParameter("from"));
                    request.setAttribute("to", request.getParameter("to"));
                    request.setAttribute("did", request.getParameter("did"));
                    request.setAttribute("pids", pids);

                    // Truy vấn lại dữ liệu từ DBContext
                    PlanDBContext planDb = new PlanDBContext();
                    Plan existingPlan = planDb.get(planId);
                    request.setAttribute("plan", existingPlan);

                    PlanCampaignDBContext campaignDb = new PlanCampaignDBContext();
                    ArrayList<PlanCampain> campaigns = campaignDb.list(planId);
                    request.setAttribute("campaigns", campaigns);

                    DepartmentDBContext departmentDb = new DepartmentDBContext();
                    ArrayList<Department> departments = departmentDb.get("workshop");
                    request.setAttribute("depts", departments);

                    ProductDBContext productDb = new ProductDBContext();
                    ArrayList<Product> products = productDb.list();
                    request.setAttribute("products", products);

                    // Forward lại trang update.jsp với dữ liệu đã nhập và thông báo lỗi
                    request.getRequestDispatcher("/view/productionplan/update.jsp").forward(request, response);
                    return;
                }

                c.setPl(plan);
                if (c.getQuantity() != 0 && c.getEffort() != 0) {
                    plan.getCampains().add(c);
                }
            }

            PlanDBContext db = new PlanDBContext();
            db.update(plan);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
        }

        response.sendRedirect("list");
    }

}
