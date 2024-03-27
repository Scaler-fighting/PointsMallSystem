package com.pointsmallsystem.www.dao;

import com.pointsmallsystem.www.po.Customer;
import com.pointsmallsystem.www.util.GetListUtil;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressDao {
    public static List<String> selectAddress(Customer customer ) throws SQLException, ClassNotFoundException {
        //连接数据库
        Connection conn=DatabaseConnection.getConnection();
        //定义查询地址的sql语句
        String sql="Select address from address where user_id='"+customer.getUserId()+"'";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        List<String> addresses= GetListUtil.getAddresses(rs);
        return addresses;
    }

    public static void addAddress(Customer customer,String newAddress) throws SQLException, ClassNotFoundException {
        Connection conn=DatabaseConnection.getConnection();
        String sql="insert into address(user_id,address,isdefault) values(?,?,?)";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,customer.getUserId());
        pstmt.setString(2,newAddress);
        pstmt.setBoolean(3,false);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

    }
    public static void updateAddress(Customer customer, String newAddress, String oldAddress) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "update address set address=? where user_id=? and address=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, newAddress);
        pstmt.setInt(2, customer.getUserId());
        pstmt.setString(3, oldAddress);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }


    public static void deleteAddress(Customer customer,String addressToDelete) throws SQLException, ClassNotFoundException {
        Connection conn= DatabaseConnection.getConnection();
        String sql="delete from address where address=? and user_id=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,addressToDelete);
        pstmt.setInt(2,customer.getUserId());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

    }


}
