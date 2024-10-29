
package model;
import java.sql.Date;

/**
 * Daitqhe182481
 * @author Zeldais
 */
public class Schedule {
  private int id;
    private PlanCampaign cam;
    private Date date;
    private String shift;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlanCampaign getCam() {
        return cam;
    }

    public void setCam(PlanCampaign cam) {
        this.cam = cam;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
