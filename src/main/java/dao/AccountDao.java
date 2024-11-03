package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import context.DBContext;
import models.Account;

public class AccountDao {
    private static final String INSERT_USER_SQL = "INSERT INTO Account (username, password, roleID) VALUES (?, ?, ?)";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM Account WHERE username = ?";
    private static final String CHECK_LOGIN_SQL = "SELECT Role.roleName FROM Account INNER JOIN Role ON Account.roleID = Role.roleID WHERE Account.username = ? AND Account.password = ?";

    // Lưu tài khoản mới vào database
    public void saveUser(Account account) {
        try (Connection connection = DBContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setInt(3, account.getRoleID());  // Lưu trực tiếp roleID từ Account
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy thông tin tài khoản dựa trên username
    public Account getAccountByUsername(String username) {
        Account account = null;
        try (Connection connection = DBContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                account = new Account(rs.getInt("accountID"), rs.getString("username"), rs.getString("password"), rs.getInt("roleID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    // Kiểm tra đăng nhập
    public String checkLogin(String username, String password) {
        try (Connection connection = DBContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN_SQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString("roleName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
