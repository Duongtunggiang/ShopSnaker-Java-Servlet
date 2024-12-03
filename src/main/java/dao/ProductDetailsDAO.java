package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.ProductDetails;

public class ProductDetailsDAO {
	private DBContext dbContext = new DBContext();

    // Lấy tất cả chi tiết sản phẩm
    public List<ProductDetails> getAllProductDetails() {
        List<ProductDetails> details = new ArrayList<>();
        String sql = "SELECT * FROM ProductDetails";
        try (Connection conn = dbContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ProductDetails detail = new ProductDetails();
                detail.setDetailId(rs.getInt("DetailID"));
                detail.setProductId(rs.getInt("ProductID"));
                detail.setDescription(rs.getString("Description"));
                details.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }

    // Lấy chi tiết sản phẩm theo ProductID
    public ProductDetails getProductDetailsByProductId(int productId) {
        ProductDetails detail = null;
        String sql = "SELECT * FROM ProductDetails WHERE ProductID = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    detail = new ProductDetails();
                    detail.setDetailId(rs.getInt("DetailID"));
                    detail.setProductId(rs.getInt("ProductID"));
                    detail.setDescription(rs.getString("Description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detail;
    }
}
