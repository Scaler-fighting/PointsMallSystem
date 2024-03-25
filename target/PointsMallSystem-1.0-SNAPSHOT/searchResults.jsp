<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pointsmallsystem.www.po.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pointsmallsystem.www.dao.SelectProductByKewordsDao" %>
<html>
<head>
    <title>Search Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .products-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding: 20px;
        }

        .product {
            width: 300px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin: 10px;
            padding: 15px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .product h2 {
            color: #333;
            font-size: 20px;
            margin-bottom: 10px;
        }

        .product p {
            color: #666;
        }
    </style>
</head>
<body>
<body>
<div class="sidebar">
    <h3>导航</h3>
    <ul>
        <li><a href="register.jsp?type=customer">客户端注册</a></li>
        <li><a href="register.jsp?type=merchant">商户端注册</a></li>
        <li><a href="login.jsp?type=customer">客户端登录</a></li>
        <li><a href="login.jsp?type=merchant">商户端登录</a></li>
        <li><a href="search.html">搜索</a></li>
    </ul>
</div>
<h1>产品列表</h1>
<div class="products-container">
    <% List<Product> products= SelectProductByKewordsDao.searchProducts(request.getParameter("keyword"));
        for (Product product : products) { %>
    <div class="product">
        <h2><%= product.getProductName() %></h2>
        <p>价格: <%= product.getPrice() %></p>
        <p>库存: <%= product.getStock() %></p>
        <p>评价: <%= product.getDescription() %></p>
        <p>销量：<%=product.getSales()%></p>
        <p>发布时间：<%=product.getPublishDate()%></p>
    </div>
    <% } %>
</div>
</body>
</html>
