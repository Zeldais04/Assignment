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
public class ScheduleDBContext extends DBContext<Schedule> {

    public void insertSchedules(ArrayList<Schedule> schedules) {
        String sql = "INSERT INTO [dbo].[ScheduleCampaign]\n"
                + "           ([camid]\n"
                + "           ,[date]\n"
                + "           ,[shift]\n"
                + "           ,[quantity])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;

        try {
            connection.setAutoCommit(false);  // Bắt đầu giao dịch

            stm = connection.prepareStatement(sql);
            for (Schedule model : schedules) {
                stm.setInt(1, model.getCam().getId());
                stm.setDate(2, model.getDate());
                stm.setString(3, model.getShift());
                stm.setInt(4, model.getQuantity());
                stm.executeUpdate();  // Thực hiện lệnh insert
            }

            connection.commit();  // Commit sau khi insert hết các bản ghi
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (connection != null) {
                    connection.rollback();  // Rollback nếu có lỗi
                }
            } catch (SQLException ex1) {
                Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (stm != null) {
                try {
                    stm.close();  // Đóng PreparedStatement sau khi hoàn thành
                } catch (SQLException ex) {
                    Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                connection.setAutoCommit(true);  // Đặt lại chế độ auto-commit
                connection.close();  // Đóng kết nối
            } catch (SQLException ex) {
                Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Schedule model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Schedule model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Schedule> list() {
        ArrayList<Schedule> schedules = new ArrayList<>();
        try {
            String sql = "SELECT scid, camid, date, shift, quantity FROM [dbo].[ScheduleCampaign]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setId(rs.getInt("scid"));
                s.setId(rs.getInt("camid"));
                s.setDate(rs.getDate("date"));
                s.setShift(rs.getString("shift"));
                s.setQuantity(rs.getInt("quantity"));
                schedules.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedules;
    }

    @Override
    public Schedule get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Schedule model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
