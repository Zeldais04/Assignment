
package model;

/**
 * Daitqhe182481
 * @author Zeldais
 */
public class PlanCampaign {
private int id;
    private Plan pl;
    private Product p;
    private int quantity;
    private float effort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plan getPl() {
        return pl;
    }

    public void setPl(Plan pl) {
        this.pl = pl;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getEffort() {
        return effort;
    }

    public void setEffort(float effort) {
        this.effort = effort;
    }
    
}
