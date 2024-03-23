package com.pointsmallsystem.www.dao;

import com.pointsmallsystem.www.po.Product;
import com.pointsmallsystem.www.util.GetListUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectAllProductsDao {
    public static List<Product> getProducts() throws SQLException, ClassNotFoundException {
        Connection connnectioin= DatabaseConnection.getConnection();

        String sql="select * from product";
        PreparedStatement pst= connnectioin.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();

        List<Product> products=GetListUtil.getProducts(rs);
        rs.close();
        connnectioin.close();
        return products;
    }

}
