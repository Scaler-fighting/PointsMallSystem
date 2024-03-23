package com.pointsmallsystem.www.dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    @Test
    public  void  getConnection() throws ClassNotFoundException {

        try {
            // 加载 MySQL 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 连接数据库
            String url = "jdbc:mysql://localhost:3306/pointsmallsystem";
            String username = "root";
            String password = "20050821";

            try  {
                // 连接成功
                Connection connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database connection successful");
                //关闭资源
                connection.close();
            } catch (SQLException e) {
                // 连接失败
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // 驱动加载失败
            e.printStackTrace();
        }
    }
}
