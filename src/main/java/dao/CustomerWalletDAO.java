package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import context.DBContext;

public class CustomerWalletDAO {
    private DBContext dbContext;

    public CustomerWalletDAO() {
        this.dbContext = new DBContext();
    }

    public void createCustomerWallet(int accountID) {
        String sql = "INSERT INTO CustomerWallet (CustomerID, Balance) VALUES (?, 0)";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountID);
            ps.executeUpdate();
            System.out.println("Ví cho Customer được tạo thành công với AccountID: " + accountID);
        } catch (SQLException e) {
            System.err.println("Lỗi khi tạo ví: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateBalance(int customerId, BigDecimal amount) throws SQLException {
        String sql = "UPDATE CustomerWallet SET Balance = Balance + ? WHERE CustomerID = ?";
        try (Connection connection = dbContext.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBigDecimal(1, amount);
            ps.setInt(2, customerId);
            ps.executeUpdate();
        }
    }

    public BigDecimal getBalance(int customerId) throws SQLException {
        String sql = "SELECT Balance FROM CustomerWallet WHERE CustomerID = ?";
        try (Connection connection = dbContext.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("Balance");
                }
            }
        }
        return BigDecimal.ZERO; // Trả về 0 nếu không tìm thấy
    }
}
