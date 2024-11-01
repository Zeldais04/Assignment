package controller.productionplan;

import dal.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author Zeldais
 */
public class ProductionPlanCreateController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDBContext dbProduct = new ProductDBContext();
        DepartmentDBContext dbDept = new DepartmentDBContext();
        request.setAttribute("products", dbProduct.list());
        request.setAttribute("depts", dbDept.get("workshop"));
        request.getRequestDispatcher("/view/productionplan/create.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] pids = request.getParameterValues("pid");

        Plan plan = new Plan();
        plan.setStartTime(Date.valueOf(request.getParameter("from")));

        // Kiểm tra giá trị endTime phải lớn hơn startTime
        Date startTime = plan.getStartTime();
        Date endTime = Date.valueOf(request.getParameter("to"));

        if (endTime.after(startTime)) {
            plan.setEndTime(endTime);
        } else {
            // Gửi thông báo lỗi tới console log và giữ nguyên dữ liệu đã nhập
            System.out.println("Thời gian bắt đầu phải trước thời gian kết thúc");
            request.setAttribute("errorMessage", "Thời gian bắt đầu phải trước thời gian kết thúc");
            request.setAttribute("from", request.getParameter("from"));
            request.setAttribute("to", request.getParameter("to"));
            request.setAttribute("did", request.getParameter("did"));
            request.setAttribute("pids", pids);

            // Truyền lại các thông tin cần thiết như danh sách phòng ban, sản phẩm
            DepartmentDBContext departmentDb = new DepartmentDBContext();
            ArrayList<Department> departments = departmentDb.list();
            request.setAttribute("depts", departments);

            ProductDBContext productDb = new ProductDBContext();
            ArrayList<Product> products = productDb.list();
            request.setAttribute("products", products);

            request.getRequestDispatcher("/view/productionplan/create.jsp").forward(request, response);
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
                // Gửi thông báo lỗi tới console log và giữ nguyên dữ liệu đã nhập
                System.out.println("Số lượng phải là số nguyên và effort phải là số thực");
                request.setAttribute("errorMessage", "Số lượng phải là số nguyên và effort phải là số thực");
                request.setAttribute("from", request.getParameter("from"));
                request.setAttribute("to", request.getParameter("to"));
                request.setAttribute("did", request.getParameter("did"));
                request.setAttribute("pids", pids);

                // Truyền lại các thông tin cần thiết như danh sách phòng ban, sản phẩm
                DepartmentDBContext departmentDb = new DepartmentDBContext();
                ArrayList<Department> departments = departmentDb.list();
                request.setAttribute("depts", departments);

                ProductDBContext productDb = new ProductDBContext();
                ArrayList<Product> products = productDb.list();
                request.setAttribute("products", products);

                request.getRequestDispatcher("/view/productionplan/create.jsp").forward(request, response);
                return;
            }

            c.setPl(plan);
            if (c.getQuantity() != 0 && c.getEffort() != 0) {
                plan.getCampains().add(c);
            }
        }

        if (!plan.getCampains().isEmpty()) {
            PlanDBContext db = new PlanDBContext();
            db.insert(plan);
            response.getWriter().println("created a new plan!");
        } else {
            response.getWriter().println("your plan did not have any campains");
        }
    }

}
