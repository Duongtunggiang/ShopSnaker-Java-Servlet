package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import context.DBContext;
import models.Saler;

public class SalerDao {
	public Saler getSalerByAccountID(int accountID) {
        String sql = "SELECT * FROM Saler WHERE accountID = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Saler saler = new Saler();
                    saler.setSalerID(rs.getInt("SalerID"));
                    saler.setAccountID(rs.getInt("AccountID"));
                    saler.setFirstName(rs.getString("FirstName"));
                    saler.setLastName(rs.getString("LastName"));
                    saler.setPhoneNumber(rs.getString("PhoneNumber"));
                    saler.setAddress(rs.getString("Address"));
                    saler.setFrom(rs.getString("From"));
                    return saler;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSaler(Saler saler) {
        String sql = "UPDATE Saler SET FirstName = ?, LastName = ?, PhoneNumber = ?, Address = ?, `From` = ? WHERE SalerID = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, saler.getFirstName());
            ps.setString(2, saler.getLastName());
            ps.setString(3, saler.getPhoneNumber());
            ps.setString(4, saler.getAddress());
            ps.setString(5, saler.getFrom());
            ps.setInt(6, saler.getSalerID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
