package com.pointsmallsystem.www.servlet;

import com.pointsmallsystem.www.dao.UpdatePointsDao;
import com.pointsmallsystem.www.po.Customer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/RechargePoints")
public class RechargePointsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rechargePoints = request.getParameter("rechargePoints");
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (rechargePoints == null || rechargePoints.isEmpty() || !rechargePoints.matches("\\d+")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("错误：请输入有效的充值积分数。");
            return;
        }

        int rechargePointValue = Integer.parseInt(rechargePoints);
        try {
            UpdatePointsDao.updatePoints(customer, rechargePointValue);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("<script>alert('您已成功充值了 " + rechargePointValue + " 积分');</script>");
            response.setHeader("refresh", "1;url=Customer.jsp" +
                    ""); // 1秒后跳转到登录页面
        } catch (SQLException | ClassNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("错误：充值过程中出现问题，请稍后再试。");
            e.printStackTrace(); // Consider logging the exception instead of printing it to the console.
        }
    }
}
