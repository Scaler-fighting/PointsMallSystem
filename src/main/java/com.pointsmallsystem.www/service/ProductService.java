package com.pointsmallsystem.www.service;

import com.pointsmallsystem.www.dao.SelectAllProductsDao;
import com.pointsmallsystem.www.po.Product;
import com.pointsmallsystem.www.util.DateUtil;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductService {
    public static List<Product> getProductSortedBySales(String sortOrder) throws SQLException, ClassNotFoundException {
        List<Product> products= SelectAllProductsDao.getProducts();
        if("asc".equals(sortOrder)){
            products.sort(Comparator.comparingInt(Product::getSales));
        }else{
            products.sort(Comparator.comparingInt(Product::getSales).reversed());
        }
        return products;
    }
    public static List<Product> getProductSortedByDate(String sortOrder) throws SQLException, ClassNotFoundException {
        List<Product> products=SelectAllProductsDao.getProducts();
        Comparator<Product> comparator=Comparator.comparing(product-> {
            try {
                return DateUtil.converStringToDate(product.getPublishDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
        if("asc".equals(sortOrder)){
            products.sort(comparator);
        } else{
            products.sort(comparator.reversed());
        }
        return products;
    }

    public static List<Product> getProductsByPage(String sortBy,String sortOrder,int currentPage, int pageSize) throws SQLException, ClassNotFoundException {

        List<Product> allProducts = SelectAllProductsDao.getProducts(); // 获取所有产品列表
        if(allProducts!=null&&!allProducts.isEmpty()) {
            if ("sales".equals(sortBy)) {
                if ("asc".equals(sortOrder)) {
                    allProducts = ProductService.getProductSortedBySales("asc"); // 按销量升序排序
                }else {
                    allProducts = ProductService.getProductSortedBySales("desc"); // 按销量降序排序
                }
            } else {
                if ("asc".equals(sortOrder)) {
                    allProducts = ProductService.getProductSortedByDate("asc"); // 按日期升序排序
                } else {
                    allProducts = ProductService.getProductSortedByDate("desc"); // 按日期降序排序
                }
            }
        }else{
            allProducts=new ArrayList<>();
        }
        List<Product> products=new ArrayList<>();

        int startIndex=(currentPage-1)*pageSize;
        int endIndex=Math.min(startIndex+pageSize,allProducts.size());

        for(int i=startIndex;i<endIndex;i++){
            products.add(allProducts.get(i));
        }
        return products;
    }
}

