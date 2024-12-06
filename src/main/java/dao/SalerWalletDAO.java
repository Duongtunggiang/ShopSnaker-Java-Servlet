package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import context.DBContext;

public class SalerWalletDAO {
    private DBContext dbContext;

    public SalerWalletDAO() {
        this.dbContext = new DBContext();
    }

    public void createSalerWallet(int salerId) throws SQLException {
        String sql = "INSERT INTO SalerWallet (SalerID, Balance) VALUES (?, ?)";
        try (Connection connection = dbContext.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, salerId);
            ps.setBigDecimal(2, BigDecimal.ZERO); // Ví khởi tạo với số dư 0
            ps.executeUpdate();
        }
    }

    public void updateBalance(int salerId, BigDecimal amount) throws SQLException {
        String sql = "UPDATE SalerWallet SET Balance = Balance + ? WHERE SalerID = ?";
        try (Connection connection = dbContext.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBigDecimal(1, amount);
            ps.setInt(2, salerId);
            ps.executeUpdate();
        }
    }

    public BigDecimal getBalance(int salerId) throws SQLException {
        String sql = "SELECT Balance FROM SalerWallet WHERE SalerID = ?";
        try (Connection connection = dbContext.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, salerId);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("Balance");
                }
            }
        }
        return BigDecimal.ZERO; // Trả về 0 nếu không tìm thấy
    }
}

