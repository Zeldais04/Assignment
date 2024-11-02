package controller.schedule;

import controller.accesscontrol.BaseRBACController;
import dal.ScheduleDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Schedule;
import model.accesscontrol.User;

/**
 *
 * @author Zeldais
 */
public class ScheduleListController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        ScheduleDBContext scheduleDb = new ScheduleDBContext();
        ArrayList<Schedule> schedules = scheduleDb.list();
        request.setAttribute("schedules", schedules);
        request.getRequestDispatcher("/view/schedule/list.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
