package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.EmployeeDao;
import model.Employee;
import util.Tool;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public Employee selectByUsername(String username) {
        Connection conn = Tool.getDb();
        String sql = "SELECT * FROM employee WHERE username = ?";
        Employee emp = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    emp = new Employee();
                    emp.setId(rs.getInt("id"));
                    emp.setName(rs.getString("name"));
                    emp.setUsername(rs.getString("username"));
                    emp.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }
}