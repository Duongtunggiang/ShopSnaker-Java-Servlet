package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.Role;

public class RoleDAO {
    private DBContext dbContext = new DBContext();

    // Lấy tất cả các vai trò
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM Role";
        
        try (Connection conn = dbContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Role role = new Role();
                role.setRoleID(rs.getInt("RoleID"));
                role.setRoleName(rs.getString("RoleName"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return roles;
    }

    // Thêm vai trò mới
    public boolean addRole(Role role) {
        String sql = "INSERT INTO Role (RoleName) VALUES (?)";
        
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, role.getRoleName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

