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

        .content {
            flex: 1;
            padding: 20px;
        }

        h1 {
            color: rgba(0, 0, 0, 0.65);
            margin-bottom: 20px;
            text-align: center;
            text-shadow: 2px 2px 2px rgba(0,0,0,0.3);
        }

        p {
            font-weight: 600;
            text-align: center;
            color: #FFFFFF;
        }

        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        li {
            text-align: center;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 15px;
        }

        a {
            text-decoration: none;
            color: #FFFFFF;
            transition: color 0.3s;
        }

        a:hover {
            color: #FFFFFF;
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
        .modal {
            display: none; /* 初始隐藏模态框 */
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 10% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 400px;
        }



    </style>
</head>
<body>

<div class="sidebar">
    <h3>导航</h3>
    <ul>
        <li><a href="ModifyPersonInfo.jsp">个人信息修改</a></li>
        <li><a href="#">收货地址管理</a></li>
        <li><a href="RechargePoints.jsp">积分充值</a></li>
        <li><a href="#">购物车</a></li>
    </ul>
</div>
<div class="content" id="productContent">
    <div id="products">
        <!-- 这里将显示从 browse.jsp 加载的内容 -->
        <%@include file="browse.jsp" %>

</div>

<div class="username">
    欢迎，<%= session.getAttribute("username") %>
</div>
</div>
</body>
</html>
