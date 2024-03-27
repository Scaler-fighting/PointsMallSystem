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

@WebServlet("/updateShoppingCart")
public class UpdateShoppingCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int productId= Integer.parseInt(request.getParameter("productId"));
        HttpSession session=request.getSession();
        Customer customer= (Customer) session.getAttribute("customer");
        if(customer!=null) {
            boolean result = false;
            try {
                result=ShoppingCartDao.updateShoppingCart(quantity, productId, customer);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            request.getSession().setAttribute("customer", customer);
            response.setContentType("text/html;charset=utf-8");
            if (result) {
                response.getWriter().println("<script>alert('购买数量修改成功');window.location='ShoppingCart.jsp';</script>");
            }else{
                response.getWriter().println("<script>alert('修改失败');window.location='ShoppingCart.jsp';</script>");
            }
        } else{
        System.out.println("客户信息为空");
    }




    }
}
