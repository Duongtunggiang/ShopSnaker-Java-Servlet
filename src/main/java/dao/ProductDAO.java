package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import context.DBContext;
import models.Booking;
import models.Product;

public class ProductDAO {
    private DBContext dbContext = new DBContext();

    public List<Product> getAllProducts() {
        String sql = "SELECT p.ProductID, p.ProductName, p.Price, p.Quality, p.ProductImagePath, p.Status, c.CategoryName " +
                     "FROM Product p " +
                     "JOIN Category c ON p.CategoryID = c.CategoryID";

        List<Product> products = new ArrayList<>();
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getBigDecimal("Price"));
                product.setQuality(rs.getInt("Quality"));
                product.setProductImagePath(rs.getString("ProductImagePath"));
                product.setStatus(rs.getString("Status"));
                product.setCategoryName(rs.getString("CategoryName")); // Thêm CategoryName vào Product

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }



    //Sản phẩm có thể bán:::
    public List<Product> getSellableProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.* FROM product p JOIN booking b ON p.productId = b.productId WHERE b.status = 'Có thể bán'";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setQuality(rs.getInt("quality"));
                product.setProductImagePath(rs.getString("productImagePath"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    public List<Product> getAvailableProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.ProductID, p.ProductName, p.Price, p.Quality, p.ProductImagePath, p.Status, c.CategoryName "
                + "FROM Product p "
                + "JOIN Category c ON p.CategoryID = c.CategoryID "
                + "WHERE p.Status = 'Có thể bán'";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
//    public Product mapResultSetToProduct(ResultSet rs) throws SQLException {
//        Product product = new Product();
//
//        product.setProductID(rs.getInt("ProductID")); // Ánh xạ cột ProductID
//        product.setSalerID(rs.getInt("SalerID")); // Ánh xạ cột SalerID
//        product.setCategoryID(rs.getInt("CategoryID")); // Ánh xạ cột CategoryID
//        product.setProductName(rs.getString("ProductName")); // Ánh xạ cột ProductName
//        product.setQuality(rs.getInt("Quality")); // Ánh xạ cột Quality
//        product.setPrice(rs.getBigDecimal("Price")); // Ánh xạ cột Price
//        product.setDiscount(rs.getBigDecimal("Discount")); // Ánh xạ cột Discount
//        product.setProductImagePath(rs.getString("ProductImagePath")); // Ánh xạ cột ProductImagePath
//        product.setColor(rs.getString("Color")); // Ánh xạ cột Color
//        product.setStyle(rs.getString("Style")); // Ánh xạ cột Style
//        product.setStatus(rs.getString("Status")); // Ánh xạ cột Status
//        product.setCategoryName(rs.getString("CategoryName"));
//
//        return product;
//    }
    public Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();

        product.setProductID(rs.getInt("ProductID"));
        product.setProductName(rs.getString("ProductName"));
        product.setQuality(rs.getInt("Quality"));
        product.setPrice(rs.getBigDecimal("Price"));
        product.setProductImagePath(rs.getString("ProductImagePath"));
        product.setStatus(rs.getString("Status"));
        product.setCategoryName(rs.getString("CategoryName"));

        return product;
    }

//    public List<Product> getAvailableProducts() {
//        String sql = "SELECT p.ProductID, p.SalerID, p.CategoryID, p.ProductName, p.Quality, p.Price, p.Discount, " +
//                     "p.ProductImagePath, p.Color, p.Style, b.Status AS Status " +
//                     "FROM Product p LEFT JOIN Booking b ON p.ProductID = b.ProductID WHERE b.Status = 'Có thể bán'";
//
//        List<Product> products = new ArrayList<>();
//        try (Connection conn = dbContext.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                Product product = new Product();
//                product.setProductID(rs.getInt("ProductID"));
//                product.setSalerID(rs.getInt("SalerID"));
//                product.setCategoryID(rs.getInt("CategoryID"));
//                product.setProductName(rs.getString("ProductName"));
//                product.setQuality(rs.getInt("Quality"));
//                product.setPrice(rs.getBigDecimal("Price"));
//                product.setDiscount(rs.getBigDecimal("Discount"));
//                product.setProductImagePath(rs.getString("ProductImagePath"));
//                product.setColor(rs.getString("Color"));
//                product.setStyle(rs.getString("Style"));
//
//                // Tạo đối tượng Booking và gán status từ Booking
//                Booking booking = new Booking();
//                booking.setStatus(rs.getString("Status"));
//                product.setBooking(booking);
//
//                products.add(product);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return products;
//    }

    public List<Product> getProductsWithBookingStatus() {
        String sql = "SELECT p.ProductID, p.SalerID, p.CategoryID, p.ProductName, p.Quality, p.Price, " +
                     "p.Discount, p.ProductImagePath, p.Color, p.Style, " +
                     "COALESCE(b.Status, 'Không xác định') AS Status " +
                     "FROM Product p LEFT JOIN Booking b ON p.ProductID = b.ProductID";

        List<Product> products = new ArrayList<>();
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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

                // Gán trạng thái Booking vào Product
                Booking booking = new Booking();
                booking.setStatus(rs.getString("Status"));
                product.setBooking(booking);

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }



    public List<Product> getProductsByCategoryId(String id) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE categoryID = ?";

        try (Connection conn = dbContext.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt("productID"));
                product.setSalerID(resultSet.getInt("salerID"));
                product.setCategoryID(resultSet.getInt("categoryID"));
                product.setProductName(resultSet.getString("productName"));
                product.setQuality(resultSet.getInt("quality"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setDiscount(resultSet.getBigDecimal("discount"));
                product.setProductImagePath(resultSet.getString("productImagePath"));
                product.setColor(resultSet.getString("color"));
                product.setStyle(resultSet.getString("style"));
                
                productList.add(product);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public int addProduct(Product product) {
        String sql = "INSERT INTO Product (SalerID, CategoryID, ProductName, Quality, Price, Discount, ProductImagePath, Color, Style, Status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, product.getSalerID());
            stmt.setInt(2, product.getCategoryID());
            stmt.setString(3, product.getProductName());
            stmt.setInt(4, product.getQuality());
            stmt.setBigDecimal(5, product.getPrice());
            stmt.setBigDecimal(6, product.getDiscount());
            stmt.setString(7, product.getProductImagePath());
            stmt.setString(8, product.getColor());
            stmt.setString(9, product.getStyle());
            stmt.setString(10, "Chưa thể bán"); // Trạng thái mặc định

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public boolean updateStatus(int productId, String status) {
        String sql = "UPDATE Product SET Status = ? WHERE ProductID = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, productId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void createBooking(int productId) {
        String sql = "INSERT INTO Booking (ProductID, Status) VALUES (?, 'Chưa thể bán')";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Lấy sản phẩm theo ID
    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT * FROM Product WHERE ProductID = ?";
        
        try (Connection conn = dbContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setSalerID(rs.getInt("SalerID"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setProductName(rs.getString("ProductName"));
                product.setQuality(rs.getInt("Quality"));
                product.setPrice(rs.getBigDecimal("Price"));
                product.setDiscount(rs.getBigDecimal("Discount"));
                product.setProductImagePath(rs.getString("productImagePath"));
                product.setColor(rs.getString("Color"));
                product.setStyle(rs.getString("Style"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    // Cập nhật sản phẩm
    public boolean updateProduct(Product product) {
        String sql = "UPDATE Product SET SalerID = ?, CategoryID = ?, ProductName = ?, Quality = ?, Price = ?, Discount = ?, ProductImagePath = ?, Color = ?, Style = ? WHERE ProductID = ?";
        
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
            stmt.setInt(10, product.getProductID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Product> getProductsByCategories(List<Integer> categoryIds) {
        List<Product> productList = new ArrayList<>();

        if (categoryIds == null || categoryIds.isEmpty()) {
            return productList; // Trả về danh sách rỗng nếu không có ID nào
        }

        String placeholders = categoryIds.stream()
                                          .map(id -> "?")
                                          .collect(Collectors.joining(","));
        String sql = "SELECT p.*, b.Status AS Status " +
                     "FROM product p " +
                     "LEFT JOIN booking b ON p.productID = b.productID " +
                     "WHERE p.categoryID IN (" + placeholders + ") " +
                     "AND b.Status = 'Có thể bán'";

        try (Connection conn = dbContext.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            // Gán giá trị cho từng placeholder
            for (int i = 0; i < categoryIds.size(); i++) {
                statement.setInt(i + 1, categoryIds.get(i));
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt("productID"));
                product.setSalerID(resultSet.getInt("salerID"));
                product.setCategoryID(resultSet.getInt("categoryID"));
                product.setProductName(resultSet.getString("productName"));
                product.setQuality(resultSet.getInt("quality"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setDiscount(resultSet.getBigDecimal("discount"));
                product.setProductImagePath(resultSet.getString("productImagePath"));
                product.setColor(resultSet.getString("color"));
                product.setStyle(resultSet.getString("style"));

                // Lấy trạng thái Status từ Booking
                Booking booking = new Booking();
                booking.setStatus(resultSet.getString("Status"));
                product.setBooking(booking);

                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
    public boolean deleteProduct(int productId) {
        boolean isDeleted = false;
        String sql = "DELETE FROM product WHERE productid = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }

    
    //Update Status
    public boolean updateProductStatus(int productId, String status) {
        String sql = "UPDATE Product SET Status = ? WHERE productID = ?";
        
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            stmt.setInt(2, productId);
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	public List<Product> getProductsByCategoriesAndStatus(List<Integer> categoryIds, String status) {
	    List<Product> products = new ArrayList<>();
	    String sql = "SELECT * FROM Product WHERE CategoryID IN (?) AND Status = ?";
	
	    try (Connection conn = DBContext.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	         
	        stmt.setString(1, categoryIds.stream()
	                                     .map(String::valueOf)
	                                     .collect(Collectors.joining(",")));
	        stmt.setString(2, status);
	        try (ResultSet rs = stmt.executeQuery()) {
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
	                product.setStatus(rs.getString("Status"));
	                products.add(product);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	    return products;
	}


}
