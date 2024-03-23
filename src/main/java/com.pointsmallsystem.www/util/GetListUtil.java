package com.pointsmallsystem.www.util;

import com.pointsmallsystem.www.po.Product;
import com.pointsmallsystem.www.po.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetListUtil {
    public static List<Product> getProducts(ResultSet rs) throws SQLException {
        List<Product> products=new ArrayList<>();
        while(rs.next()){
            Product product=new Product();
            product.setProductName(rs.getString("product_name"));
            product.setPrice(rs.getDouble("price"));
            product.setProductId(rs.getInt("product_id"));
            product.setStock(rs.getInt("stock"));
            product.setDescription(rs.getString("description"));
            product.setCategoryId(rs.getInt("category_id"));
            product.setSales(rs.getInt("sales"));
            product.setPublishDate(rs.getString("publish_time"));

            products.add(product);
        }
        return products;
    }
    public static List<User> getCustomers(ResultSet rs) throws SQLException {
        List<User> customers=new ArrayList<>();
        while (rs.next()){
            User customer=new User();
            String gender=rs.getString("gender");
            String email=rs.getString("email");
            String phone=rs.getString("phone");
            customer.setName(rs.getString("username"));
            customer.setPassword(rs.getString("password"));
            customer.setGender(gender);
            customer.setPhone(phone);
            customer.setEmail(email);
            customers.add(customer);
        }
        return customers;
    }

    public static List<User> getMerchants(ResultSet rs) throws SQLException {
        List<User> merchants=new ArrayList<>();
        while (rs.next()){
            User customer=new User();
            String gender=rs.getString("gender");
            String email=rs.getString("email");
            String phone=rs.getString("phone");
            customer.setName(rs.getString("username"));
            customer.setPassword(rs.getString("password"));
            customer.setGender(gender);
            customer.setPhone(phone);
            customer.setEmail(email);
            merchants.add(customer);
        }
        return merchants;
    }
}
