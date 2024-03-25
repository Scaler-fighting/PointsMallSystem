package com.pointsmallsystem.www.dao;

import com.pointsmallsystem.www.po.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePointsDao {
    public static void updatePoints(Customer customer,int rechargePoint) throws SQLException, ClassNotFoundException {
        Connection conn=DatabaseConnection.getConnection();
        int newPoints= customer.getPoints()+rechargePoint;
        String sql="Update customers set points='"+newPoints+"' where user_id='"+customer.getUserId()+"'";
        PreparedStatement pstmt=conn.prepareStatement(sql);
         pstmt.executeUpdate();

    }
}
