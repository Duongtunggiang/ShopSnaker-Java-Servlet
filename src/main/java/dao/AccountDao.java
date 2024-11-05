package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import context.DBContext;
import models.Account;
import models.Admin;
import models.Customer;
import models.Saler;

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
 // Phương thức để lấy salerID dựa trên username
//    public static Integer getSalerIDByUsername(String username) {
//        Integer salerID = null;
//        String sql = "SELECT AccountID FROM Account WHERE username = ?";
//
//        try (Connection connection = DBContext.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, username);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    salerID = resultSet.getInt("salerID");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return salerID;
//    }
    
	/*
	 * Trước tiên, hãy cập nhật AccountDao để lấy SalerID từ username qua bảng
	 * Account. Phương thức này sẽ truy vấn AccountID từ bảng Account, sau đó sử
	 * dụng AccountID để truy vấn SalerID từ bảng Saler.
	 */
  
    public Integer getSalerIDByUsername(String username) {
        Integer salerID = null;
        String sql = "SELECT s.SalerID " +
                     "FROM Account a " +
                     "JOIN Saler s ON a.AccountID = s.AccountID " +
                     "WHERE a.Username = ?";

        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    salerID = resultSet.getInt("SalerID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salerID;
    
    }

    
    private static final String LayID = "SELECT AccountID FROM Account WHERE username = ?";
    public int getAccountId(String username) {
        int accountId = -1; // Giá trị mặc định nếu không tìm thấy
        try (Connection connection = DBContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LayID)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                accountId = resultSet.getInt("AccountID"); // Lấy ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountId; // Trả về ID hoặc -1 nếu không tìm thấy
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
    public Integer getSalerIDByAccountID(int accountId) {
        String sql = "SELECT SalerID FROM Saler WHERE AccountID = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("SalerID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // return null if not found
    }
    
    public Integer getAccountIDByUsername(String username) {
        String sql = "SELECT AccountID FROM Account WHERE Username = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("AccountID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // return null if not found
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
    
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO Customer (accountID) VALUES (?)";
    private static final String INSERT_SALER_SQL = "INSERT INTO Saler (accountID) VALUES (?)";
    private static final String INSERT_ADMIN_SQL = "INSERT INTO Admin (accountID) VALUES (?)";

    // Lưu thông tin Customer
    public void saveCustomer(Customer customer) {
        try (Connection connection = DBContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
            preparedStatement.setInt(1, customer.getAccountID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lưu thông tin Saler
    public void saveSaler(Saler saler) {
        try (Connection connection = DBContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SALER_SQL)) {
            preparedStatement.setInt(1, saler.getAccountID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lưu thông tin Admin
    public void saveAdmin(Admin admin) {
        try (Connection connection = DBContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {
            preparedStatement.setInt(1, admin.getAccountID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
