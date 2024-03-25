package com.pointsmallsystem.www.servlet;

import com.pointsmallsystem.www.dao.SelectByNamePasswordDao;
import com.pointsmallsystem.www.po.Customer;
import com.pointsmallsystem.www.po.Merchant;
import com.pointsmallsystem.www.po.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");


        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        try {
            if ("merchant".equals(type)) {
                Merchant merchant = SelectByNamePasswordDao.SelectMerchants(userName, password);
                HttpSession session = request.getSession();
                if (merchant==null) {
                    request.setAttribute("error", "Invalid username or password");
                    request.getRequestDispatcher("login.jsp?type=merchant").forward(request, response);
                } else {
                    session.setAttribute("username", userName);
                    //response.sendRedirect("Merchant.jsp");

                    session.setAttribute("merchant",merchant);
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().println("<script>alert('登录成功');</script>");
                    response.setHeader("refresh", "1;url=Merchant.jsp" +
                            ""); // 1秒后跳转到登录页面
                }
            } else {
                // 调用查询客户用户信息的方法
                Customer customer = SelectByNamePasswordDao.SelectCustomers(userName, password);
                HttpSession session = request.getSession();
                if (customer==null) {
                    request.setAttribute("error", "Invalid username or password");
                    request.getRequestDispatcher("login.jsp?type=customer").forward(request, response);
                } else {
                    session.setAttribute("username", userName);
                    //response.sendRedirect("Customer.jsp");

                    session.setAttribute("customer",customer);
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().println("<script>alert('登录成功');</script>");
                    response.setHeader("refresh", "1;url=Customer.jsp" +
                            ""); // 1秒后跳转到登录页面
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}