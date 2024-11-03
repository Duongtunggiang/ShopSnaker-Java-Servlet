package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.Product;

public class ProductDAO {
	private DBContext dbContext = new DBContext();

    // Lấy tất cả các sản phẩm
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        
        try (Connection conn = dbContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setSalerID(rs.getInt("SalerID"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setProductName(rs.getString("ProductName"));
                product.setQuality(rs.getInt("Quality"));
                product.setPrice(rs.getBigDecimal("Price"));
                product.setDiscount(rs.getBigDecimal("Discount"));
                product.setProductImagePath(rs.getString("ProductImagePath"));
                product.setColor(rs.getString("Color"));
                product.setStyle(rs.getString("Style"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }

    // Thêm sản phẩm mới
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO Product (SalerID, CategoryID, ProductName, Quality, Price, Discount, ProductImagePath, Color, Style) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, product.getSalerID());
            stmt.setInt(2, product.getCategoryID());
            stmt.setString(3, product.getProductName());
            stmt.setInt(4, product.getQuality());
            stmt.setBigDecimal(5, product.getPrice());
            stmt.setBigDecimal(6, product.getDiscount());
            stmt.setString(7, product.getProductImagePath());
            stmt.setString(8, product.getColor());
            stmt.setString(9, product.getStyle());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
