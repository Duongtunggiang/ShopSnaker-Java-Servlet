package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.Gift;
import models.Transactions;
public class TransactionsDAO {
	private DBContext dbContext = new DBContext();

    // Lấy tất cả giao dịch
    public List<Transactions> getAllTransactions() {
        List<Transactions> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transactions";
        try (Connection conn = dbContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Transactions transaction = new Transactions();
                transaction.setTransactionsId(rs.getInt("TransactionsID"));
                transaction.setBookingId(rs.getInt("BookingID"));
                transaction.setAmount(rs.getBigDecimal("Amount"));
                transaction.setTransactionsDate(rs.getTimestamp("TransactionsDate").toLocalDateTime());
                transaction.setStatus(rs.getString("Status"));
                transaction.setPaymentMethod(rs.getString("PaymentMethod"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // Lấy giao dịch theo BookingID
    public List<Transactions> getTransactionsByBookingId(String bookingId) {
        List<Transactions> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transactions WHERE BookingID = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bookingId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transactions transaction = new Transactions();
                    transaction.setTransactionsId(rs.getInt("TransactionsID"));
                    transaction.setBookingId(rs.getInt("BookingID"));
                    transaction.setAmount(rs.getBigDecimal("Amount"));
                    transaction.setTransactionsDate(rs.getTimestamp("TransactionsDate").toLocalDateTime());
                    transaction.setStatus(rs.getString("Status"));
                    transaction.setPaymentMethod(rs.getString("PaymentMethod"));
                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
