package com.pointsmallsystem.www.dao;

import com.pointsmallsystem.www.po.Customer;
import com.pointsmallsystem.www.po.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDao {
    public static List<Product> selectShoppingCart(Customer customer) throws SQLException, ClassNotFoundException {
        Connection conn=DatabaseConnection.getConnection();
        String sql="select product_id,quantity from shopping_cart where customer_id=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1, customer.getUserId());
        ResultSet rs=pstmt.executeQuery();
        List<Product> shoppingCart=new ArrayList<>();
        while(rs.next()){
            int productId = rs.getInt("product_id");
            int quantity=rs.getInt("quantity");
            Product product=ProductDao.selectProductsById(productId,quantity);
            shoppingCart.add(product);
        }
        pstmt.close();
        conn.close();
        return shoppingCart;
    }

    public static boolean addShoppingCart(int productId,Customer customer,int quantity) throws SQLException, ClassNotFoundException {
        Connection conn=DatabaseConnection.getConnection();
        String sql="insert into shopping_cart(customer_id,product_id,quantity) values(?,?,?)";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,customer.getUserId());
        pstmt.setInt(2,productId);
        pstmt.setInt(3,quantity);
        int row=pstmt.executeUpdate();
        boolean flag=false;
        if(row>0){
            flag=true;
        }
        Product product=ProductDao.selectProductsById(productId,quantity);
        List<Product> prodcuts=customer.getShoppingCart();
        prodcuts.add(product);
        customer.setShoppingCart(prodcuts);
        pstmt.close();
        conn.close();
        return flag;
    }

    public static boolean updateShoppingCart(int quantity,int productId,Customer customer) throws SQLException, ClassNotFoundException {
        Connection conn=DatabaseConnection.getConnection();
        String sql="Update shopping_cart set quantity=? where product_id=? and customer_id=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,quantity);
        pstmt.setInt(2,productId);
        pstmt.setInt(3,customer.getUserId());
        int row=pstmt.executeUpdate();
        boolean flag=false;
        if(row>0){
            flag=true;
        }
       customer.setShoppingCart(selectShoppingCart(customer));
        pstmt.close();
        conn.close();
        return flag;
    }
    public static boolean deleteShoppingCart(int productId,Customer customer) throws SQLException, ClassNotFoundException {
        Connection conn=DatabaseConnection.getConnection();
        String sql="delete from shopping_cart where product_id=? and customer_id=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,productId);
        pstmt.setInt(2,customer.getUserId());
        int row=pstmt.executeUpdate();
        boolean flag=false;
        if(row>0){
            flag=true;
        }
        customer.setShoppingCart(selectShoppingCart(customer));
        pstmt.close();
        conn.close();
        return flag;


    }
}
