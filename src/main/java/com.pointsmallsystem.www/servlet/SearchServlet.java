package com.pointsmallsystem.www.servlet;

import com.pointsmallsystem.www.dao.ProductDao;
import com.pointsmallsystem.www.po.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Product> products;
        try {
            products = ProductDao.searchProducts(keyword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("products", products);
        request.getRequestDispatcher("searchResults.jsp").forward(request,response);

    }

}
