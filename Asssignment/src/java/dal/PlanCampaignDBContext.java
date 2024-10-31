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
public class PlanCampaignDBContext extends DBContext<PlanCampain> {

    @Override
    public void insert(PlanCampain model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(PlanCampain model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(PlanCampain model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<PlanCampain> list(int planId) {
        ArrayList<PlanCampain> campaigns = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            // Truy vấn các chiến dịch sản xuất từ bảng PlanCampaign
            String sql_campaign = "SELECT [camid], [plid], [pid], [quantity], [unitEffort] FROM [PlanCampaign] WHERE [plid] = ?";
            try (PreparedStatement stm_campaign = connection.prepareStatement(sql_campaign)) {
                stm_campaign.setInt(1, planId);
                try (ResultSet rs_campaign = stm_campaign.executeQuery()) {
                    while (rs_campaign.next()) {
                        PlanCampain campaign = new PlanCampain();
                        campaign.setId(rs_campaign.getInt("camid"));
                        campaign.setQuantity(rs_campaign.getInt("quantity"));
                        campaign.setEffort(rs_campaign.getFloat("unitEffort"));

                        // Lấy thông tin sản phẩm từ bảng Product
                        int productId = rs_campaign.getInt("pid");
                        String sql_product = "SELECT [pid], [pname] FROM [Product] WHERE [pid] = ?";
                        try (PreparedStatement stm_product = connection.prepareStatement(sql_product)) {
                            stm_product.setInt(1, productId);
                            try (ResultSet rs_product = stm_product.executeQuery()) {
                                if (rs_product.next()) {
                                    Product product = new Product();
                                    product.setId(rs_product.getInt("pid"));
                                    product.setName(rs_product.getString("pname"));

                                    // Set product vào campaign
                                    campaign.setP(product);
                                }
                            }
                        }

                        // Thêm chiến dịch vào danh sách
                        campaigns.add(campaign);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanCampaignDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanCampaignDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return campaigns;
    }

    @Override
    public PlanCampain get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PlanCampain> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
