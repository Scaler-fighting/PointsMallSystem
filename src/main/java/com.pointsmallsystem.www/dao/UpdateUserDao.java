package com.pointsmallsystem.www.dao;

import com.pointsmallsystem.www.po.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUserDao {
    public void addUser(User user,String type) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // 获取数据库连接
            conn = DatabaseConnection.getConnection();

            // 插入用户信息到数据库
            String sql1= "INSERT INTO merchant (user_name, password,phone, email, address, gender) VALUES (?, ?, ?, ?, ?, ?)";
            String sql2="INSERT INTO customer(user_name,password,phone,email,gender)values(?,?,?,?,?)";

            if ("merchant".equals(type)) {
                stmt = conn.prepareStatement(sql1);
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getPhone());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, user.getAddress());
                stmt.setString(6, user.getGender());
            }
            if("customer".equals(type)) {
                stmt=conn.prepareStatement(sql2);
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getPhone());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, user.getGender());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接等资源
            stmt.close();
            conn.close();

        }
    }
}
