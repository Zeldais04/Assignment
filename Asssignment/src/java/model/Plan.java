package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Daitqhe182481
 *
 * @author Zeldais
 */
public class Plan {

    private int id;
    private Date startTime;
    private Date endTime;
    private Department d;
    private ArrayList<PlanCampain> campains = new ArrayList<>();

    public ArrayList<PlanCampain> getCampains() {
        return campains;
    }

    public void setCampains(ArrayList<PlanCampain> campains) {
        this.campains = campains;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Department getD() {
        return d;
    }

    public void setD(Department d) {
        this.d = d;
    }

}
