
package model;

/**
 * Daitqhe182481
 * @author Zeldais
 */
public class WokerSchedule {
  private int id;
    private Schedule sc;
    private Employee e;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Schedule getSc() {
        return sc;
    }

    public void setSc(Schedule sc) {
        this.sc = sc;
    }

    public Employee getE() {
        return e;
    }

    public void setE(Employee e) {
        this.e = e;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
