package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.Customer;

public class CustomerDAO {
	private DBContext dbContext = new DBContext();

    // Lấy tất cả khách hàng
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        
        try (Connection conn = dbContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setAccountID(rs.getInt("AccountID"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setFrom(rs.getString("From"));
                customer.setAddress(rs.getString("Address"));
                customer.setPhoneNumber(rs.getString("PhoneNumber"));
                customer.setEmail(rs.getString("Email"));
                customer.setProfileImagePath(rs.getString("ProfileImagePath"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customers;
    }

    // Thêm khách hàng mới
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (AccountID, FirstName, LastName, [From], Address, PhoneNumber, Email, ProfileImagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, customer.getAccountID());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getFrom());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getPhoneNumber());
            stmt.setString(7, customer.getEmail());
            stmt.setString(8, customer.getProfileImagePath());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
