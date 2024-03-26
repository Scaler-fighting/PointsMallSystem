package com.pointsmallsystem.www.servlet;

import com.pointsmallsystem.www.dao.AddressDao;
import com.pointsmallsystem.www.po.Customer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/addAddress")
public class AddAddressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session=  request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            String newAddress = request.getParameter("newAddress");
            List<String> addresses = customer.getAddressList();
            if (addresses != null) {
                addresses.add(newAddress);
                customer.setAddressList(addresses);
                try {
                    AddressDao.addAddress(customer, newAddress);
                    request.getSession().setAttribute("customer", customer);
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().println("<script>alert('地址添加成功');window.location='address.jsp';</script>");
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // 处理addresses为null的情况
                System.out.println("您的地址为空");
            }
        } else {
            // 处理customer为null的情况
            System.out.println("未查询到您的用户信息");
        }
    }


}
