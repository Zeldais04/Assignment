package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 * Daitqhe182481
 *
 * @author Zeldais
 */
public class PlanDBContext extends DBContext<Plan> {

    @Override
    public void insert(Plan model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_plan = "INSERT INTO [Plan]\n"
                    + "           ([startTime]\n"
                    + "           ,[endTime]\n"
                    + "           ,[did])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            String sql_select_plan = "SELECT @@IDENTITY as plid";
            String sql_insert_campain = "INSERT INTO [PlanCampaign]\n"
                    + "           ([plid]\n"
                    + "           ,[pid]\n"
                    + "           ,[quantity]\n"
                    + "           ,[unitEffort])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan);
            stm_insert_plan.setDate(1, model.getStartTime());
            stm_insert_plan.setDate(2, model.getEndTime());
            stm_insert_plan.setInt(3, model.getD().getId());
            stm_insert_plan.executeUpdate();

            PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
            ResultSet rs = stm_select_plan.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt("plid"));
            }
            for (PlanCampain campain : model.getCampains()) {
                PreparedStatement stm_insert_campain = connection.prepareStatement(sql_insert_campain);
                stm_insert_campain.setInt(1, model.getId());
                stm_insert_campain.setInt(2, campain.getP().getId());
                stm_insert_campain.setInt(3, campain.getQuantity());
                stm_insert_campain.setFloat(4, campain.getEffort());
                stm_insert_campain.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(Plan plan) {
        try {
            connection.setAutoCommit(false);

            // Cập nhật kế hoạch sản xuất
            String sql_update_plan = "UPDATE [Plan] SET [startTime] = ?, [endTime] = ?, [did] = ? WHERE [plid] = ?";
            PreparedStatement stm_update_plan = connection.prepareStatement(sql_update_plan);
            stm_update_plan.setDate(1, plan.getStartTime());
            stm_update_plan.setDate(2, plan.getEndTime());
            stm_update_plan.setInt(3, plan.getD().getId());
            stm_update_plan.setInt(4, plan.getId());
            stm_update_plan.executeUpdate();

            // Xóa tất cả các chiến dịch cũ liên quan đến kế hoạch này
            String sql_delete_campaigns = "DELETE FROM [PlanCampaign] WHERE [plid] = ?";
            PreparedStatement stm_delete_campaigns = connection.prepareStatement(sql_delete_campaigns);
            stm_delete_campaigns.setInt(1, plan.getId());
            stm_delete_campaigns.executeUpdate();

            // Thêm lại các chiến dịch mới
            String sql_insert_campaign = "INSERT INTO [PlanCampaign] ([plid], [pid], [quantity], [unitEffort]) VALUES (?, ?, ?, ?)";
            for (PlanCampain campaign : plan.getCampains()) {
                PreparedStatement stm_insert_campaign = connection.prepareStatement(sql_insert_campaign);
                stm_insert_campaign.setInt(1, plan.getId());
                stm_insert_campaign.setInt(2, campaign.getP().getId());
                stm_insert_campaign.setInt(3, campaign.getQuantity());
                stm_insert_campaign.setFloat(4, campaign.getEffort());
                stm_insert_campaign.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, rollbackEx);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Plan model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Plan> list() {
        ArrayList<Plan> plans = new ArrayList<>();
        try {
            String sql = "SELECT [plid], [startTime], [endTime], [did] FROM [Plan]";
            try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Plan plan = new Plan();
                    plan.setId(rs.getInt("plid"));
                    plan.setStartTime(rs.getDate("startTime"));
                    plan.setEndTime(rs.getDate("endTime"));
                    // Set department or other related objects here if needed
                    plans.add(plan);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return plans;
    }

    @Override
    public Plan get(int id) {
        Plan plan = null;
        try {
            String sql = "SELECT [plid], [startTime], [endTime], [did] FROM [Plan] WHERE [plid] = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        plan = new Plan();
                        plan.setId(rs.getInt("plid"));
                        plan.setStartTime(rs.getDate("startTime"));
                        plan.setEndTime(rs.getDate("endTime"));
                        // Thiết lập các thuộc tính khác nếu cần
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return plan;

    }

    public void delete(int planId) {
        try {
            connection.setAutoCommit(false);

            // Xóa tất cả các chiến dịch liên quan đến kế hoạch sản xuất này
            String sql_delete_campaigns = "DELETE FROM [PlanCampaign] WHERE [plid] = ?";
            PreparedStatement stm_delete_campaigns = connection.prepareStatement(sql_delete_campaigns);
            stm_delete_campaigns.setInt(1, planId);
            stm_delete_campaigns.executeUpdate();

            // Xóa kế hoạch sản xuất
            String sql_delete_plan = "DELETE FROM [Plan] WHERE [plid] = ?";
            PreparedStatement stm_delete_plan = connection.prepareStatement(sql_delete_plan);
            stm_delete_plan.setInt(1, planId);
            stm_delete_plan.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, rollbackEx);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
