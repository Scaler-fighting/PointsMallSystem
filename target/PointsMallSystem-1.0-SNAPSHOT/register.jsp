<%@ page contentType="text/html;charset=UTF-8" language="java"%>
        <html>
        <head>
        <title>User Registration</title>
        <style>
        body {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        background-color: #f4f4f4;
        font-family: Arial, sans-serif;
        }
        form {
        width: 300px;
        padding: 20px;
        border: 1px solid #ccc;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
        color: #333;
        margin-bottom: 20px;
        text-align: center;
        text-shadow: 2px 2px 2px rgba(0,00,0.3);
        }
        label {
        display: block;
        margin-bottom: 5px;
        }
        input[type="text"],
        input[type="password"],
        input[type="email"] {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 3px;
        }
        select {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 3px;
        }
        input[type="radio"] {
        margin-right: 5px;
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
        .gender {
        margin-top: 10px;
        }
        </style>
        </head>
        <body>

        <form action="register" method="post">
        <input type="hidden" name="type" value="<%= request.getParameter("type") %>">

<h1>User Registration</h1>

<div>
    <% if("merchant".equals(request.getParameter("type"))){ %>
    <label>Type: Merchant</label>
    <%}%>
    <% if("customer".equals(request.getParameter("type"))){ %>
    <label>Type: Customer</label>
    <%}%>
</div>

<label>Username:</label>
<input type="text" name="username">

<label>Password:</label>
<input type="password" name="password">

<label>Phone:</label>
<input type="text" name="phone">

<label>Email:</label>
<input type="text" name="email">

<% if ("merchant".equals(request.getParameter("type"))) { %>
<label>Address:</label>
<input type="text" name="address">
<% } %>


<label>Gender:</label>
<input type="radio" name="gender" value="男"> Male
<input type="radio" name="gender" value="女"> Female
</div>

<input type="submit" value="Register">
</form>
</body>
</html>
