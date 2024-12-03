package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.Gift;

public class GiftDAO {
	private DBContext dbContext = new DBContext();

    // Lấy tất cả quà tặng
    public List<Gift> getAllGifts() {
        List<Gift> gifts = new ArrayList<>();
        String sql = "SELECT * FROM Gift";
        try (Connection conn = dbContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Gift gift = new Gift();
                gift.setGiftId(rs.getInt(0));
                gift.setProductId(rs.getInt(1));
                gift.setGiftName(rs.getString(2));
                gift.setDescription(rs.getString(3));
                gift.setQuantity(rs.getInt(4));
                gifts.add(gift);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gifts;
    }

    // Lấy quà tặng theo ProductID
    public List<Gift> getGiftsByProductId(int productId) {
        List<Gift> gifts = new ArrayList<>();
        String sql = "SELECT * FROM Gift WHERE ProductID = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Gift gift = new Gift();
                    gift.setGiftId(rs.getInt(0));
                    gift.setProductId(rs.getInt(1));
                    gift.setGiftName(rs.getString(2));
                    gift.setDescription(rs.getString(3));
                    gift.setQuantity(rs.getInt(4));
                    gifts.add(gift);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gifts;
    }
}
