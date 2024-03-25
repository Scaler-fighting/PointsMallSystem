package com.pointsmallsystem.www.servlet;

import com.pointsmallsystem.www.dao.SelectAllProductsDao;
import com.pointsmallsystem.www.po.Product;
import com.pointsmallsystem.www.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
public class BrowseProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String sortBy = request.getParameter("sortBy");
            String sortOrder = request.getParameter("sortOrder");

            int pageSize = 25;
            int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
            List<Product> allProdouts=SelectAllProductsDao.getProducts();
            // 获取当前页需要显示的产品数据，并根据用户选择的排序方式和排序顺序进行排序
            List<Product> sortedProducts = ProductService.getProductSorted(sortBy, sortOrder);

            request.setAttribute("products", sortedProducts); // 设置当前页的产品列表到请求属性中
            request.setAttribute("sortBy",sortBy);
            request.setAttribute("sortOrder",sortOrder);
            request.getRequestDispatcher("/sortedProducts.jsp").forward(request, response); // 转发至浏览产品页面
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

