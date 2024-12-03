package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.Size;

public class SizeDAO {
	private DBContext dbContext = new DBContext();

    // Lấy tất cả kích thước sản phẩm
    public List<Size> getAllSizes() {
        List<Size> sizes = new ArrayList<>();
        String sql = "SELECT * FROM Size";
        try (Connection conn = dbContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Size size = new Size();
                size.setSizeId(rs.getInt(0));
                size.setProductId(rs.getInt(1));
                size.setSizeValue(rs.getString(2));
                sizes.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizes;
    }

    // Lấy kích thước theo ProductID
    public List<Size> getSizesByProductId(int productId) {
        List<Size> sizes = new ArrayList<>();
        String sql = "SELECT * FROM Size WHERE ProductID = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Size size = new Size();
                    size.setSizeId(rs.getInt(0));
                    size.setProductId(rs.getInt(1));
                    size.setSizeValue(rs.getString(2));
                    sizes.add(size);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizes;
    }
}
