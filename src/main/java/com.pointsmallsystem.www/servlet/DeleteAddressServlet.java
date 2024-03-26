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

@WebServlet("/deleteAddress")
public class DeleteAddressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session= request.getSession();
        Customer customer= (Customer) session.getAttribute("customer");
        if(customer!=null) {
            String addressToDelete = request.getParameter("oldAddress");
            List<String> addresses = customer.getAddressList();
            if (addresses != null && addressToDelete != null) {
                try {
                    AddressDao.deleteAddress(customer,addressToDelete);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                addresses.remove(addressToDelete);
                customer.setAddressList(addresses);
                request.getSession().setAttribute("customer", customer);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("<script>alert('地址删除成功');</script>");
                response.setHeader("refresh", "1;url=address.jsp" +
                        ""); // 1秒后跳转到登录页面
            }else{
                System.out.println("地址列表为空或者待删除的地址为空");
            }
        }else{
            System.out.println("客户信息为空");

        }
    }
}
