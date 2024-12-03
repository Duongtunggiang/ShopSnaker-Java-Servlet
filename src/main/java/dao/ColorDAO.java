package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.Color;
import models.Gift;
public class ColorDAO {
	private DBContext dbContext = new DBContext();

    // Lấy tất cả màu sắc
    public List<Color> getAllColors() {
        List<Color> colors = new ArrayList<>();
        String sql = "SELECT * FROM Color";
        try (Connection conn = dbContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Color color = new Color();
                color.setColorId(rs.getInt(0));
                color.setProductId(rs.getInt(1));
                color.setColorValue(rs.getString(2));
                colors.add(color);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colors;
    }

    // Lấy màu sắc theo ProductID
    public List<Color> getColorsByProductId(int productId) {
        List<Color> colors = new ArrayList<>();
        String sql = "SELECT * FROM Color WHERE ProductID = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Color color = new Color();
                    color.setColorId(rs.getInt(0));
                    color.setProductId(rs.getInt(1));
                    color.setColorValue(rs.getString(2));
                    colors.add(color);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colors;
    }
}
