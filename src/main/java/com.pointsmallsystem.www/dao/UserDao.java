package com.pointsmallsystem.www.dao;

import com.pointsmallsystem.www.po.Customer;
import com.pointsmallsystem.www.po.Merchant;
import com.pointsmallsystem.www.po.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    public static void addMerchant(Merchant merchant ) throws SQLException,RuntimeException,ClassNotFoundException{
        Connection conn =  DatabaseConnection.getConnection();
            // 插入用户信息到数据库
        String sql= "INSERT INTO merchants (user_name, password,phone, email, address, gender) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, merchant.getName());
        stmt.setString(2, merchant.getPassword());
        stmt.setString(3, merchant.getPhone());
        stmt.setString(4, merchant.getEmail());
        stmt.setString(5, merchant.getAddress());
        stmt.setString(6, merchant.getGender());

        stmt.executeUpdate();

            // 关闭数据库连接等资源
        stmt.close();
        conn.close();


    }
    public static void addCustoemr(Customer customer) throws RuntimeException, SQLException, ClassNotFoundException {
        Connection conn =  DatabaseConnection.getConnection();

        String sql = "INSERT INTO customers(user_name,password,phone,email,gender)values(?,?,?,?,?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, customer.getName());
        stmt.setString(2, customer.getPassword());
        stmt.setString(3, customer.getPhone());
        stmt.setString(4, customer.getEmail());
        stmt.setString(5, customer.getGender());

        stmt.executeUpdate();
        stmt.close();
        conn.close();


    }


        public static boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
            Connection conn=DatabaseConnection.getConnection();
            String sql="Update customers set user_name=?,gender=?,password=?,email=?,phone=? where user_id=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,customer.getName());
            pstmt.setString(2,customer.getGender());
            pstmt.setString(3,customer.getPassword());
            pstmt.setString(4,customer.getEmail());
            pstmt.setString(5,customer.getPhone());
            pstmt.setInt(6,customer.getUserId());

            boolean success=false;
            int rowAffected=pstmt.executeUpdate();

            if(rowAffected>0){
                success=true;
            }
            if(pstmt!=null)pstmt.close();
            if(conn!=null)conn.close();
            return success;

        }
        public static boolean updateMerchant(Merchant merchant) throws SQLException, ClassNotFoundException {
            Connection conn=DatabaseConnection.getConnection();
            String sql="Update merchants set user_name=?,gender=?,password=?,email=?,phone=?,address=? where user_id=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,merchant.getName());
            pstmt.setString(2,merchant.getGender());
            pstmt.setString(3,merchant.getPassword());
            pstmt.setString(4,merchant.getEmail());
            pstmt.setString(5,merchant.getPhone());
            pstmt.setString(6,merchant.getAddress());
            pstmt.setInt(7,merchant.getUserId());

            boolean flag=false;
            int rowAffected=pstmt.executeUpdate();
            if(rowAffected>0){
                flag=true;
            }
            pstmt.close();
            conn.close();
            return flag;
        }




}