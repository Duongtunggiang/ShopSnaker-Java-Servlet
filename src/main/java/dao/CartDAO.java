package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.CartItem;

public class CartDAO {
    public List<CartItem> getCartItemsByCustomerId(int customerId) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT ci.CartItemID, ci.ProductID, ci.Quantity, ci.Price, p.ProductName "
                   + "FROM CartItem ci "
                   + "JOIN Cart c ON ci.CartID = c.CartID "
                   + "JOIN Product p ON ci.ProductID = p.ProductID "
                   + "WHERE c.CustomerID = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setCartItemId(rs.getInt("CartItemID"));
                item.setProductId(rs.getInt("ProductID"));
                item.setQuantity(rs.getInt("Quantity"));
                item.setPrice(rs.getBigDecimal("Price"));
                item.setProductName(rs.getString("ProductName"));
                cartItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItems;
    }
    public int createCartForCustomer(int customerId) {
        String sql = "INSERT INTO Cart (CustomerID) VALUES (?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, customerId);
            ps.executeUpdate();

            // Lấy CartID vừa tạo
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Lỗi
    }

    public int getCartIdByCustomerId(int customerId) {
        String sql = "SELECT CartID FROM Cart WHERE CustomerID = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("CartID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Không tìm thấy
    }
    public boolean addCartItem(CartItem cartItem) {
        String sql = "INSERT INTO CartItem (CartID, ProductID, Quantity, Price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartItem.getCartId());
            ps.setInt(2, cartItem.getProductId());
            ps.setInt(3, cartItem.getQuantity());
            ps.setBigDecimal(4, cartItem.getPrice());
//            ps.setString(5, cartItem.getProductName());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Lỗi
    }
    public boolean deleteCartItem(int cartItemId) {
        String sql = "DELETE FROM CartItem WHERE CartItemID = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartItemId);
            return ps.executeUpdate() > 0; // Nếu xóa thành công, trả về true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Nếu có lỗi hoặc không tìm thấy CartItem, trả về false
    }

}
