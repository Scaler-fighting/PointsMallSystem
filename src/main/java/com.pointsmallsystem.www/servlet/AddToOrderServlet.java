package com.pointsmallsystem.www.servlet;

import com.pointsmallsystem.www.po.Customer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addToServlet")
public class AddToOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        Customer customer= (Customer) session.getAttribute("customer");

    }
}
