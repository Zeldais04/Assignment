package controller.schedule;

import controller.accesscontrol.BaseRBACController;
import dal.PlanCampaignDBContext;
import dal.PlanDBContext;
import dal.ScheduleDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import model.Plan;
import model.PlanCampain;
import model.Schedule;
import model.accesscontrol.User;

/**
 *
 * @author Zeldais
 */
public class ScheduleCreateController extends BaseRBACController {

    public ArrayList<Date> getDateList(Date startDate, Date endDate) {
        ArrayList<Date> dateList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (!calendar.getTime().after(endDate)) {
            dateList.add(new Date(calendar.getTimeInMillis()));
            calendar.add(Calendar.DATE, 1); // Tăng ngày
        }

        return dateList;
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        String planIdParam = request.getParameter("planId");
        if (planIdParam == null || planIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Plan ID is missing");
            return;
        }

        int planId = Integer.parseInt(planIdParam);

        // Lấy kế hoạch từ CSDL
        PlanDBContext planDb = new PlanDBContext();
        Plan plan = planDb.get(planId);
        ArrayList<Date> dateList = getDateList(plan.getStartTime(), plan.getEndTime());
        request.setAttribute("time", dateList);

        if (plan == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Plan not found");
            return;
        }

        PlanCampaignDBContext campaignDb = new PlanCampaignDBContext();
        ArrayList<PlanCampain> campaigns = campaignDb.list(planId);

        request.setAttribute("plan", plan);
        request.setAttribute("campaigns", campaigns);
        request.getRequestDispatcher("/view/schedule/create.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        String planIdParam = request.getParameter("planId");
        if (planIdParam == null || planIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Plan ID is missing");
            return;
        }

        int planId = Integer.parseInt(planIdParam);
        ScheduleDBContext scheduleDb = new ScheduleDBContext();

        PlanCampaignDBContext campaignDb = new PlanCampaignDBContext();
        ArrayList<PlanCampain> campaigns = campaignDb.list(planId);
        ArrayList<Schedule> schedules = new ArrayList<>();

        try {
            PlanDBContext planDb = new PlanDBContext();
            Plan plan = planDb.get(planId);
            ArrayList<Date> dateList = getDateList(plan.getStartTime(), plan.getEndTime());

            for (Date date : dateList) {
                for (PlanCampain campaign : campaigns) {
                    // Lặp qua các ca k1, k2, k3
                    String[] shifts = {"k1", "k2", "k3"};
                    for (String shift : shifts) {
                        // Tạo tên biến tương ứng với từng input trong form
                        String paramName = "quantity_" + campaign.getId() + "_" + shift + "_" + date;
                        String rawQuantity = request.getParameter(paramName);

                        // In debug vào console để kiểm tra thông tin
                        System.out.println("Parameter name: " + paramName + ", value: " + rawQuantity);

                        if (rawQuantity != null && !rawQuantity.isEmpty()) {
                            try {
                                int quantity = Integer.parseInt(rawQuantity);
                                if (quantity > 0) {
                                    Schedule schedule = new Schedule();
                                    schedule.setCam(campaign);
                                    schedule.setDate(date);
                                    schedule.setShift(shift);
                                    schedule.setQuantity(quantity);
                                    schedules.add(schedule);
                                }
                            } catch (NumberFormatException e) {
                                response.getWriter().println("Invalid input for " + paramName + ": " + rawQuantity);
                            }
                        }
                    }
                }
            }

            // Insert all schedules at once
            if (!schedules.isEmpty()) {
                scheduleDb.insertSchedules(schedules);
                response.getWriter().println("Schedule created successfully!");
            } else {
                response.getWriter().println("No valid schedules were created.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format for quantity");
        }
    }

}
