package com.pointsmallsystem.www.dao;

import com.pointsmallsystem.www.po.Product;
import com.pointsmallsystem.www.util.GetListUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectProductByKewordsDao {
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
}
