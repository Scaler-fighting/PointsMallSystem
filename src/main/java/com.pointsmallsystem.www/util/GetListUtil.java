package com.pointsmallsystem.www.util;

import com.pointsmallsystem.www.dao.AddressDao;
import com.pointsmallsystem.www.dao.ShoppingCartDao;
import com.pointsmallsystem.www.po.Customer;
import com.pointsmallsystem.www.po.Merchant;
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
    public static List<Customer> getCustomers(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Customer> customers=new ArrayList<>();
        while (rs.next()){
            Customer customer=new Customer();
            String gender=rs.getString("gender");
            String email=rs.getString("email");
            String phone=rs.getString("phone");
            customer.setName(rs.getString("user_name"));
            customer.setPassword(rs.getString("password"));
            customer.setUserId(rs.getInt("user_id"));
            customer.setPoints(rs.getInt("points"));
            customer.setGender(gender);
            customer.setPhone(phone);
            customer.setEmail(email);
            customer.setAddressList(AddressDao.selectAddress(customer));
            customer.setShoppingCart(ShoppingCartDao.selectShoppingCart(customer));


            customers.add(customer);
        }
        return customers;
    }

    public static List<Merchant> getMerchants(ResultSet rs) throws SQLException {
        List<Merchant> merchants=new ArrayList<>();
        while (rs.next()){
            Merchant merchant=new Merchant();
            String gender=rs.getString("gender");
            String email=rs.getString("email");
            String phone=rs.getString("phone");
            merchant.setName(rs.getString("user_name"));
            merchant.setUserId(rs.getInt("user_id"));
            merchant.setPassword(rs.getString("password"));
            merchant.setAddress(rs.getString("address"));
            merchant.setGender(gender);
            merchant.setPhone(phone);
            merchant.setEmail(email);
            merchants.add(merchant);
        }
        return merchants;
    }

    public static List<String> getAddresses(ResultSet rs) throws SQLException {
        List<String> addresses=new ArrayList<>();
        while(rs.next()){
            String address=rs.getString("address");
            addresses.add(address);
        }
        return addresses;
    }

}
