package com.pointsmallsystem.www.service;

import com.pointsmallsystem.www.dao.ProductDao;
import com.pointsmallsystem.www.po.Product;
import com.pointsmallsystem.www.util.DateUtil;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

public class ProductServiceTest {
    @Test
    public void getProductSortedBySalesTest() throws SQLException, ClassNotFoundException {
             List<Product> products= ProductDao.getProducts();

             products.sort(Comparator.comparingInt(Product::getSales));
             System.out.println("销售量 增："+products);
             products.sort(Comparator.comparingInt(Product::getSales).reversed());
             System.out.println("销售量 减"+products);

    }

    @Test
    public void getProductSortedByDate() throws SQLException, ClassNotFoundException {
        List<Product> products=ProductDao.getProducts();
        Comparator<Product> comparator=Comparator.comparing(product-> {
            try {
                return DateUtil.converStringToDate(product.getPublishDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        products.sort(comparator);
        System.out.println("发布时间 增"+products);
        products.sort(comparator.reversed());
        System.out.println("发布时间 减"+products);
    }

}
