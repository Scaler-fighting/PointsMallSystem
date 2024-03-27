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

@WebServlet("/deleteShoppingCart")
public class DeleteShoppingCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId= Integer.parseInt(request.getParameter("productId"));
        HttpSession session=request.getSession();
        Customer customer= (Customer) session.getAttribute("customer");
        if(customer!=null){
            boolean result=false;
            try {
                result= ShoppingCartDao.deleteShoppingCart(productId,customer);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            request.getSession().setAttribute("customer", customer);
            response.setContentType("text/html;charset=utf-8");
            if (result) {
                response.getWriter().println("<script>alert('商品已从购物车中移除');window.location='ShoppingCart.jsp';</script>");
            }else{
                response.getWriter().println("<script>alert('删除失败');window.location='ShoppingCart.jsp';</script>");
            }
        }else{
            System.out.println("客户信息为空");
        }
    }
}
