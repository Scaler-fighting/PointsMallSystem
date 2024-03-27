package com.pointsmallsystem.www.servlet;

import com.pointsmallsystem.www.dao.ShoppingCartDao;
import com.pointsmallsystem.www.po.Customer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        Boolean isUserLoggedIn = (Boolean) session.getAttribute("isUserLoggedIn");
        if (isUserLoggedIn == null || !isUserLoggedIn) {
            response.sendRedirect("login.jsp");
            return;
        }
        int prodcutId= Integer.parseInt(request.getParameter("productId"));
        int quantity= Integer.parseInt(request.getParameter("quantity"));
        quantity=1;

        Customer customer= (Customer) session.getAttribute("customer");
        if(customer!=null) {
            boolean result=false;
            try {
                result = ShoppingCartDao.addShoppingCart(prodcutId, customer, quantity);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            request.getSession().setAttribute("customer", customer);
            response.setContentType("text/html;charset=utf-8");
            if (result) {
                response.getWriter().println("<script>alert('商品已添加到购物车');window.location='Customer.jsp';</script>");
            }else{
                response.getWriter().println("<script>alert('商品添加失败');window.location='ShoppingCart.jsp';</script>");
            }
        }else{
            System.out.println("客户信息为空");
        }

    }
}
