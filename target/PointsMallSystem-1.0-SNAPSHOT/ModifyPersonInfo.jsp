<%@ page import="com.pointsmallsystem.www.po.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<%--
  Created by IntelliJ IDEA.
  User: 26238
  Date: 2024/3/24
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            display: flex;
        }

        .sidebar {
            width: 150px;
            background-color: #333;
            color: #FFFFFF;
            padding: 20px;
            position: fixed;
            top: 0;
            left: -150px;
            height: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
            transition: left 0.3s;
            z-index: 1;
        }

        .sidebar:hover {
            left: 0;
        }

        .content {
            width: 400px;
            margin: 0 auto;
            margin-top: 50px;
            padding: 20px;
            border: 1px solid #ccc;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .user-info {
            text-align: center;
            margin-bottom: 20px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 8px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }

        p {
            font-weight: 600;
            text-align: center;
            color: #333;
        }

        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        li {
            text-align: center;
            padding: 15px;
            border-radius: 5px;
            background-color: #666;
            margin-bottom: 15px;
        }

        a {
            text-decoration: none;
            color: #FFFFFF;
            transition: color 0.3s;
        }

        a:hover {
            color: #CCCCCC;
        }

        .username {
            position: fixed;
            top: 20px;
            right: 20px;
            color: #333;
            font-weight: bold;
            padding: 10px;
            border: 2px solid #333;
            border-radius: 5px;
            background-color: #FFFFFF;
        }
    </style>
</head>

<body>
<div class="sidebar">
    <h3>导航</h3>
    <ul>
        <li><a href="ModifyPersonInfo.jsp">个人信息修改</a></li>
        <li><a href="#">收货地址管理</a></li>
        <li><a href="#">积分充值</a></li>
        <li><a href="#">购物车</a></li>
    </ul>
</div>
<div class="content">
    <div class="user-info">
        <h1>用户信息修改</h1>
        <form action="ModifyPersonInfo" method="post">

                <c:if test="${not empty sessionScope.customer}">
                    <label for="username">用户名：</label>
                    <input type="text" id="username" name="username" value="${sessionScope.customer.name}"><br>
                    <label for="gender">性别：</label>
                    <input type="text" id="gender" name="gender" value="${sessionScope.customer.gender}"><br>
                    <label for="password">密码：</label>
                    <input type="password" id="password" name="password" value="${sessionScope.customer.password}"><br>
                    <label for="email">邮箱：</label>
                    <input type="text" id="email" name="email" value="${sessionScope.customer.email}"><br>
                    <label for="phone">手机号：</label>
                    <input type="text" id="phone" name="phone" value="${sessionScope.customer.phone}"><br>
                    <input type="hidden" id="userId" name="userId" value="${sessionScope.customer.userId}">
                </c:if>



                <input type="submit" value="保存更改">
        </form>
        <c:if test="${not empty error}">
            <p class="error"><c:out value='${error}' /></p>
        </c:if>
    </div>

</div>

<div class="username">
    欢迎，<%= session.getAttribute("username") %>
</div>
</body>