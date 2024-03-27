package com.pointsmallsystem.www.dao;

import com.pointsmallsystem.www.po.Product;
import com.pointsmallsystem.www.util.GetListUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    public static List<Product> getProducts() throws SQLException, ClassNotFoundException {
        Connection connnectioin= DatabaseConnection.getConnection();

        String sql="select * from product";
        PreparedStatement pst= connnectioin.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();

        List<Product> products= GetListUtil.getProducts(rs);
        rs.close();
        connnectioin.close();
        return products;
    }
    public static List<Product> searchProducts(String keyword) throws SQLException, ClassNotFoundException {

        Connection conn= DatabaseConnection.getConnection();

        String sql="select *from product where product_name like ?";
        PreparedStatement stmt=conn.prepareStatement(sql);
        stmt.setString(1,"%"+keyword+"%");
        ResultSet rs=stmt.executeQuery();

        List<Product> products=GetListUtil.getProducts(rs);
        stmt.close();
        conn.close();
        return products;
    }

    public static Product selectProductsById(int productId,int quantity) throws SQLException, ClassNotFoundException {
        Connection conn=DatabaseConnection.getConnection();
        String sql="select *from product where product_id=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,productId);
        ResultSet rs=pstmt.executeQuery();
        List<Product> products=GetListUtil.getProducts(rs);
        if(products.size()==1){
            Product product=products.get(0);
            product.setQuantity(quantity);
            pstmt.close();
            conn.close();
            return product;
        }else{
            return null;
        }




    }
}
