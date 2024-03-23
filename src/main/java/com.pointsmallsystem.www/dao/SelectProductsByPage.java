package com.pointsmallsystem.www.dao;

import com.pointsmallsystem.www.po.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectProductsByPage {
    public static List<Product> getProductsByPage(String sortBy, String sortOrder, int currentPage, int pageSize) throws SQLException, ClassNotFoundException {
        List<Product> allProducts=SelectAllProductsDao.getProducts();
        List<Product> products=new ArrayList<>();

        int startIndex=(currentPage-1)*pageSize;
        int endIndex=Math.min(startIndex+pageSize,allProducts.size());

        for(int i=startIndex;i<endIndex;i++){
            products.add(allProducts.get(i));
        }
        return products;
    }


}
