package com.pointsmallsystem.www.dao;

import com.pointsmallsystem.www.po.User;
import com.pointsmallsystem.www.util.GetListUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectByNamePasswordDao {

    public static List<User> SelectCustomers(String username,String password) throws SQLException, ClassNotFoundException {
        Connection conn=DatabaseConnection.getConnection();
        String sql="select *from customers where user_name=? and password=?";

        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        ResultSet rs=pstmt.executeQuery();

        List<User> customers=GetListUtil.getCustomers(rs);
        rs.close();
        pstmt.close();
        conn.close();
        return customers;

    }
    public static List<User> SelectMerchants(String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn=DatabaseConnection.getConnection();
        String sql="select *from merchants where user_name=? and password=?";

        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        ResultSet rs=pstmt.executeQuery();

        List<User> merchants=GetListUtil.getMerchants(rs);
        rs.close();
        pstmt.close();
        conn.close();
        return merchants;
    }



}
