package dal;

import java.util.ArrayList;
import model.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Daitqhe182481
 *
 * @author Zeldais
 */
public class EmployeeDBcontext extends DBContext<Employee> {

    @Override
    public void insert(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Employee> list() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT e.eid, e.ename, e.salarylevel, e.did, d.dname FROM Employee e JOIN Dept d ON e.did = d.did";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getString("ename"));
                e.setSalaryLevel(rs.getFloat("salarylevel"));

                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                e.setD(d);

                employees.add(e);
            }
        } catch (SQLException ex) {
        }finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBcontext.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return employees;
    }

    @Override
    public Employee get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
