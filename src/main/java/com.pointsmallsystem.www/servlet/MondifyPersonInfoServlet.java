package com.pointsmallsystem.www.servlet;

import com.pointsmallsystem.www.dao.UserDao;
import com.pointsmallsystem.www.po.Customer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ModifyPersonInfo")
public class MondifyPersonInfoServlet extends HttpServlet {
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        int id = Integer.parseInt(request.getParameter("userId"));
        Customer customer=new Customer(name,password,gender,phone,email);
        customer.setUserId(id);
        boolean result= false;
        try {
            result = UserDao.updateCustomer(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(result){
            // 注册成功后弹出提示信息
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("<script>alert('修改成功');</script>");

            // 注册成功后跳转到登录页面或其他页面
            response.setHeader("refresh", "1;url=Customer.jsp" +
                    ""); // 1秒后跳转到登录页面
        }else{
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("<script>alert('修改失败');</script>");
            response.setHeader("refresh","1,url=ModifyPersonInfo.jsp"+"");
        }



    }
}
