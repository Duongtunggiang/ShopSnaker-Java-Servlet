package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.Category;

public class CategoryDAO {
    private DBContext dbContext = new DBContext();

    // Lấy tất cả các danh mục
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM Category"; // Giả sử tên bảng là 'Category'

        try (Connection conn = dbContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Category category = new Category();
                category.setCategoryID(rs.getInt("CategoryID"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setBrandImagePath(rs.getString("BrandImagePath")); // Lưu đường dẫn hình ảnh
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
    public int getCategoryIDByName(String categoryName) {
        String sql = "SELECT CategoryID FROM Category WHERE CategoryName = ?";
        
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, categoryName);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("CategoryID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }
    
    public String getCateIDbyCateName(String cateName) {
        String sql = "SELECT CategoryID FROM Category WHERE CategoryName = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, cateName);
            ResultSet resultSet = stm.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getString("CategoryID"); // Sửa thành "CategoryID" thay vì "categoryName"
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null; // Trả về null nếu không tìm thấy
    }



//    public Integer getCategoryIDByName(String categoryName) {
//        String sql = "SELECT CategoryID FROM Category WHERE CategoryName = ?";
//        try (Connection connection = DBContext.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, categoryName);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSet.getInt("CategoryID");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null; // return null if not found
//    }


    // Thêm danh mục mới
    public boolean addCategory(Category category) {
        String sql = "INSERT INTO Category (CategoryName, BrandImagePath) VALUES (?, ?)"; // Thêm BrandImagePath vào câu lệnh SQL

        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category.getCategoryName());
            stmt.setString(2, category.getBrandImagePath()); // Lưu đường dẫn hình ảnh
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
