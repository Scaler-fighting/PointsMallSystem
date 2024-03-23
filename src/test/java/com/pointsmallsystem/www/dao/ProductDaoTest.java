package com.pointsmallsystem.www.dao;

import com.pointsmallsystem.www.po.Product;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoTest {
    @Test
    public void getProductsDao() throws SQLException, ClassNotFoundException {
        Connection connnectioin= com.pointsmallsystem.www.dao.DatabaseConnection.getConnection();
        List<Product> products=new ArrayList<>();
        String sql="select product_name productName,price from product";
        PreparedStatement pst= connnectioin.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
            Product product=new Product();
            product.setProductName(rs.getString("productName"));
            product.setPrice(rs.getDouble("price"));
            products.add(product);
        }
        System.out.println(products);
    }
}
