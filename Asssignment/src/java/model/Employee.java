
package model;

/**
 * Daitqhe182481
 * @author Zeldais
 */
public class Employee {
 private int id;
    private String name;
    private float salaryLevel;
    private Department d;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(float salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public Department getD() {
        return d;
    }

    public void setD(Department d) {
        this.d = d;
    }
    
}
