<%@ page import="com.pointsmallsystem.www.po.Customer" %>
<%@ page import="com.pointsmallsystem.www.po.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
        }

        .sidebar {
            width: 200px;
            background-color: #333;
            color: #FFFFFF;
            padding: 20px;
            position: fixed;
            top: 0;
            left: -200px;
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
            flex: 1;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
            text-shadow: 2px 2px 2px rgba(0,0,0,0.2);
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            background-color: #FFFFFF;
            padding: 15px;
        }

        h2 {
            color: #333;
            font-size: 20px;
            margin-bottom: 10px;
        }

        p {
            color: #666;
        }

        .product {
            margin: 0 auto; /* 将产品信息水平居中 */
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
        }

        .product h2 {
            color: #333;
            font-size: 18px;
            margin-bottom: 5px;
        }

        .product p {
            color: #666;
            font-size: 14px;
            margin-bottom: 5px;
        }

        .product form {
            display: flex;
            align-items: center;
        }

        .product input[type="number"] {
            width: 60px;
            padding: 5px;
            margin-right: 10px;
        }

        .product button {
            padding: 5px 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .order-button {
            margin-top: 20px;
        }

        .order-button button {
            padding: 10px 20px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .username {
            position: fixed;
            top: 20px;
            right: 20px;
            padding: 10px;
            border: 2px solid #333;
            border-radius: 5px;
            color: #333;
            font-weight: bold;
            background-color: #FFFFFF;
        }
    </style>

</head>
<body>

<div class="sidebar">
    <h3 style="font-size: 18px; color: #FFFFFF; text-transform: uppercase;">导航</h3>
    <ul>
        <li><a href="ModifyPersonInfo.jsp" style="color: #333333; text-decoration: none;">个人信息修改</a></li>
        <li><a href="address.jsp" style="color: #333333; text-decoration: none;">收货地址管理</a></li>
        <li><a href="RechargePoints.jsp" style="color: #333333; text-decoration: none;">积分充值</a></li>
        <li><a href="ShoppingCart.jsp" style="color: #333333; text-decoration: none;">购物车</a></li>
    </ul>
</div>

<div class="content">
    <h1>购物车</h1>

    <ul>
        <%
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer!= null && customer.getShoppingCart()!= null) {
                List<Product> shoppingCart = customer.getShoppingCart();
                for (Product product : shoppingCart) {
        %>
        <li>
            <div class="product">
                <h2><%= product.getProductName() %></h2>
                <p>价格: <%= product.getPrice() %></p>
                <p>库存: <%= product.getStock() %></p>
                <p>评价: <%= product.getDescription() %></p>
                <p>销量: <%= product.getSales() %></p>
                <p>发布时间: <%= product.getPublishDate() %></p>
                <form action="updateShoppingCart" method="post">
                    <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                    <input type="number" name="quantity" value="<%= product.getQuantity() %>">
                    <button type="submit">更新购买数量</button>
                </form>
                <form action="addToOrder" method="post">
                    <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                    <button type="submit">添加到订单</button>
                </form>
                <!-- Add delete button -->
                <form action="deleteShoppingCart" method="post">
                    <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                    <button type="submit">删除</button>
                </form>

            </div>
        </li>
        <%
                }
            }
        %>
    </ul>

    <div class="order-button">
        <!-- Add button to add all items to order -->
        <form action="addAllToOrder" method="post">
            <button type="submit">添加所有商品到订单</button>
        </form>
        <!-- Add button to checkout order -->
        <form action="CheckoutOrder.jsp" method="post">
            <button type="submit">结算订单</button>
        </form>
    </div>
</div>

</body>
</html>