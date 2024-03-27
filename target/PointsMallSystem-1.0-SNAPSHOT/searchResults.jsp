<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pointsmallsystem.www.po.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pointsmallsystem.www.dao.ProductDao" %>
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
        .sidebar {
            width: 100px;
            background-color: #666666;
            color: #FFFFFF;
            padding: 20px;
            position: fixed;
            top: 0;
            left: -100px; /* 初始将导航栏隐藏在左侧 */
            height: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
            transition: left 0.3s; /* 使用left属性实现弹出效果 */
            z-index: 1;
        }

        .sidebar:hover {
            left: 0; /* 鼠标悬停时将导航栏展开到左侧 */
        }
        sidebar ul {
            padding: 0;
            margin: 0;
        }

        .sidebar li {
            margin-bottom: 10px;
        }

        .sidebar a {
            display: block;
            padding: 10px 0;
            color: #FFFFFF;
            text-decoration: none;
            transition: color 0.3s;
        }

        .sidebar a:hover {
            color: #FFD700;
        }
        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }
        a {
            text-decoration: none;
            color: #FFFFFF;
            transition: color 0.3s;
        }
        a:hover {
            color: #FFFFFF;
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
    <% List<Product> products= ProductDao.searchProducts(request.getParameter("keyword"));
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
