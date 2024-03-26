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

@WebServlet("/editAddress")
public class EditAddressServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer!= null) {
            int addressIndex = Integer.parseInt(request.getParameter("addressIndex"));
            String newAddress = request.getParameter("newAddress");
            String oldAddress= request.getParameter("oldAddress");
            if (newAddress!= null &&!newAddress.isEmpty()) {
                List<String> addresses = customer.getAddressList();
                if (addressIndex >= 0 && addressIndex < addresses.size()) {
                    addresses.set(addressIndex, newAddress); // Update the address at the specified index
                    customer.setAddressList(addresses);
                    session.setAttribute("customer", customer);

                    try {
                        // Update the address in the database
                        AddressDao.updateAddress(customer, newAddress,oldAddress);

                        response.setContentType("text/html;charset=utf-8");
                        response.getWriter().println("<script>alert('Address updated successfully');</script>");
                        response.setHeader("refresh", "1;url=address.jsp"); // Redirect back to the address page after update
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Invalid address index");
                }
            } else {
                System.out.println("New address parameter is empty or invalid");
            }
        } else {
            System.out.println("Customer information is empty");
        }
    }
}