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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        try {
            String sortBy = request.getParameter("sortBy");
            String sortOrder = request.getParameter("sortOrder");
            int currentPage = 1; // 默认值为1
            String pageParam = request.getParameter("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                try {
                    currentPage = Integer.parseInt(pageParam);
                } catch (NumberFormatException e) {
                    // 处理转换异常，可以记录日志或者采取其他适当的处理方式
                    e.printStackTrace();
                }
            }


            int pageSize = 25; // 设置默认值为10或者其他合适的值
            String pageSizeParam = request.getParameter("pageSize");
            if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
                try {
                    pageSize = Integer.parseInt(pageSizeParam);
                } catch (NumberFormatException e) {
                    // 处理转换异常，可以记录日志或者采取其他适当的处理方式
                    e.printStackTrace();
                }
            }

            List<Product> products=ProductService.getProductsByPage(sortBy,sortOrder,currentPage,pageSize);

            request.setAttribute("products", products); // 将排序后的产品列表设置为请求属性
            request.getRequestDispatcher("/browse.jsp").forward(request, response); // 转发请求到 browse.jsp 页面
        } catch (SQLException | ClassNotFoundException | ServletException | IOException|NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
}
