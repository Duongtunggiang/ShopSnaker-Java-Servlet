package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.*;
public class ProductImagesDAO {
	private DBContext dbContext = new DBContext();

    // Lấy tất cả hình ảnh sản phẩm
    public List<ProducImages> getAllProductImages() {
        List<ProducImages> images = new ArrayList<>();
        String sql = "SELECT * FROM ProductImages";
        try (Connection conn = dbContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ProducImages image = new ProducImages();
                image.setImageId(rs.getInt(0));
                image.setProductId(rs.getInt(1));
                image.setImagePath(rs.getString(2));
                images.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    // Lấy hình ảnh sản phẩm theo ProductID
    public List<ProducImages> getProductImagesByProductId(int productId) {
        List<ProducImages> images = new ArrayList<>();
        String sql = "SELECT * FROM ProductImages WHERE ProductID = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProducImages image = new ProducImages();
                    image.setImageId(rs.getInt(0));
                    image.setProductId(rs.getInt(1));
                    image.setImagePath(rs.getString(2));
                    images.add(image);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }
}
