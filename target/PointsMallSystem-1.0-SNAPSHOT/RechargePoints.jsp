<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 80%;
            max-width: 1200px;
            display: flex;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        .sidebar {
            width: 200px;
            background-color: #333;
            color: #FFFFFF;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .content {
            flex: 1;
            padding: 20px;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
            text-shadow: 1px 1px 1px rgba(0,0,0,0.1);
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
            border-bottom: 1px solid #ccc;
        }

        a {
            text-decoration: none;
            color: #FFFFFF;
            transition: color 0.3s;
        }

        a:hover {
            color: #FFFF00;
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
        }

    </style>
</head>
<body>

<div class="container">
    <div class="sidebar">
        <h3>导航</h3>
        <ul>
            <li><a href="ModifyPersonInfo.jsp">个人信息修改</a></li>
            <li><a href="address.jsp">收货地址管理</a></li>
            <li><a href="RechargePoints.jsp">积分充值</a></li>
            <li><a href="#">购物车</a></li>
        </ul>
    </div>
    <div class="content">
        <!-- 充值界面的 HTML 代码 -->
        <h1>积分充值</h1>
        <span>已有积分：<span id="currentPoints"></span></span> <!-- 显示现有积分的元素 -->
        <form action="RechargePoints" method="post">
            <input type="number" name="rechargePoints" placeholder="充值积分" required style="padding: 10px; margin: 10px;">
            <br>
            <input type="submit" value="充值" style="padding: 10px 20px; background-color: #333; color: #FFF; border: none; cursor: pointer;">
        </form>
    </div>
</div>
<div class="username">
    欢迎，<%= session.getAttribute("username") %>
</div>
<script>
    // 获取现有积分值并显示
    var currentPoints = <%= session.getAttribute("customer.points") %>;
    document.getElementById("currentPoints").innerHTML = currentPoints;
</script>

</body>
</html>
</html>