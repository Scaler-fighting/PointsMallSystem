package com.pointsmallsystem.www.dao;

import com.pointsmallsystem.www.po.Customer;
import com.pointsmallsystem.www.po.Merchant;
import com.pointsmallsystem.www.po.User;
import com.pointsmallsystem.www.util.GetListUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectByNamePasswordDao {

    public static Customer SelectCustomers(String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn=DatabaseConnection.getConnection();
        String sql="select *from customers where user_name=? and password=?";

        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        ResultSet rs=pstmt.executeQuery();

        List<Customer> customers=GetListUtil.getCustomers(rs);

        if (!customers.isEmpty()) {
            Customer customer = customers.get(0);
            rs.close();
            pstmt.close();
            conn.close();
            return customer;
        } else {
            // 处理没有找到匹配用户的情况，例如返回null或抛出异常
            return null; // 或者抛出自定义异常
        }



    }
    public static Merchant SelectMerchants(String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "select *from merchants where user_name=? and password=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();

        List<Merchant> merchants = GetListUtil.getMerchants(rs);
        if (!merchants.isEmpty()) {
            Merchant merchant = merchants.get(0);
            rs.close();
            pstmt.close();
            conn.close();
            return merchant;
        } else {
            // 处理没有找到匹配用户的情况，例如返回null或抛出异常
            return null; // 或者抛出自定义异常
        }



    }
}
