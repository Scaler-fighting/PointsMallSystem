package com.pointsmallsystem.www.servlet;

import com.pointsmallsystem.www.dao.UserDao;
import com.pointsmallsystem.www.po.Customer;
import com.pointsmallsystem.www.po.Merchant;
import com.pointsmallsystem.www.po.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;



@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String type=request.getParameter("type");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phone");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");


        if ("merchant".equals(type)) {
            String address = request.getParameter("address");
            Merchant merchant=new Merchant(username, password, gender, phoneNumber, email, address);
            try {
                UserDao.addMerchant(merchant);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else if("customer".equals(type)){
            Customer customer =new Customer(username,password,gender,phoneNumber,email);
            try {
                UserDao.addCustoemr(customer);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            response.getWriter().println("无效的注册类型");
        }

        // 注册成功后弹出提示信息
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("<script>alert('注册成功');</script>");

        // 注册成功后跳转到登录页面或其他页面
        response.setHeader("refresh", "1;url=login.jsp" +
                ""); // 1秒后跳转到登录页面
    }
}

