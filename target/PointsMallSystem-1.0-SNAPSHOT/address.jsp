<%@ page import="java.util.List" %>
<%@ page import="com.pointsmallsystem.www.po.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Address Management</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            width: 80%;
            max-width: 1200px;
            background-color: #fff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
            display: flex;
            margin: 20px;
        }

        .sidebar {
            width: 200px;
            background-color: #333;
            color: #fff;
            padding: 20px;
        }

        .content {
            flex: 1;
            padding: 20px;
        }

        h1 {
            text-align: center;
            font-size: 28px;
            margin-bottom: 20px;
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            margin-bottom: 10px;
        }

        a {
            color: #fff;
            text-decoration: none;
        }

        button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 10px;
            transition: all 0.3s;
        }

        .edit-btn {
            background-color: #007bff;
            color: #fff;
        }

        .delete-btn {
            background-color: #ff0000;
            color: #fff;
        }

        input[type="text"] {
            width: calc(100% - 200px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus {
            outline: none;
            border-color: #007bff;
        }

        input[type="text"]:read-only {
            background-color: #f9f9f9;
        }

        form {
            display: flex;
            align-items: center;
        }

        .add-address-form {
            margin-top: 20px;
            display: flex;
        }

        .add-address-form input[type="text"] {
            width: calc(100% - 110px);
        }

        .add-address-form button {
            width: 100px;
        }

        button:hover,
        input[type="text"]:hover {
            opacity: 0.8;
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
        <h1>Address Management</h1>
        <ul>
            <!-- Address list display and editing forms -->
            <%
                Customer customer = (Customer) session.getAttribute("customer");
                if (customer != null && customer.getAddressList() != null) {
                    List<String> addresses = customer.getAddressList();
                    int index = 0;
                    for (String address : addresses) {
            %>
            <li>
                <form class="address-form" method="post" action="editAddress">
                    <input type="hidden" name="addressIndex" value="<%=index%>">
                    <input type="hidden" name="oldAddress" value="<%=address%>">
                    <input type="text" name="newAddress" value="<%=address%>" ondblclick="enableEdit(this)">
                    <button type="submit" class="edit-btn">保存地址</button>
                    <button formaction="deleteAddress" type="submit" class="delete-btn">删除地址</button>
                </form>
            </li>
            <%
                        index++;
                    }
                }
            %>
            <li>
                <!-- Form for adding a new address -->
                <form class="add-address-form" method="post" action="addAddress">
                    <input type="text" name="newAddress" placeholder="Add Address" required>
                    <button type="submit">添加地址</button>
                </form>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
