<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pointsmallsystem.www.po.Product" %>

<%@ page import="com.pointsmallsystem.www.service.ProductService" %>
<%@ page import="com.pointsmallsystem.www.dao.ProductDao" %>

<html>
<head>
    <title>产品浏览</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f9f9f9;
            }

            .container {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
                gap: 20px;
                justify-items: center;
                padding: 20px;
            }

            .product {
                width: 100%;
                border: 1px solid #ddd;
                border-radius: 5px;
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

            .pagination {
                margin-top: 20px;
                text-align: center;
            }
            .pagination a {
                display: inline-block;
                padding: 5px 10px;
                color: #007bff;
                text-decoration: none;
            }

            .pagination a:hover {
                background-color: #f4f4f4;
            }

            .pagination .current {
                background-color: #007bff;
                color: #fff;
            }
        </style>
    </head>
    <body>
    <h1 style="text-align: center; width: 100%;">产品列表</h1>
    <div>
        <form action="products" method="get">
            <label for="sortOrder">排序方式：</label>
            <select name="sortOrder" id="sortOrder">
                <option value="asc">升序</option>
                <option value="desc">降序</option>
            </select>

            <label for="sortBy">排序指标：</label>
            <select name="sortBy" id="sortBy">
                <option value="sales">销量</option>
                <option value="publishDate">发布时间</option>
            </select>

            <input type="submit" value="提交">
        </form>
    </div>

<div class="container">
    <%

        List<Product> products = ProductDao.getProducts();
        /*String sortBy=request.getParameter("sortBy");
        String sortOrder=request.getParameter("sortOrder");
        int pageSize = 25;
        int pageNumber = (int) Math.ceil((double) products.size() / pageSize);
        int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
*/
        // 获取当前页需要显示的产品数据

       /* List<Product> currentProducts = ProductService.getProductsByPage(products,currentPage, pageSize);*/
        if(products!=null&&products.size()!=0){
        for (Product product : products) {
    %>
    <div class="product">
        <h2><%= product.getProductName() %></h2>
        <p>价格: <%= product.getPrice() %></p>
        <p>销量：<%=product.getSales()%></p>
        <p>发布时间：<%=product.getPublishDate()%></p>
        <form action="addToCart" method="post">
            <input type="hidden" name="productId" value="<%= product.getProductId() %>">
            <input type="hidden" name="quantity" value="<%=product.getQuantity()%>">
            <button type="submit">加入购物车</button>
        </form>
    </div>
    <%
        }}
    %>
</div>

<%--<!-- 分页功能 -->
<div class="pagination">
    <% if (currentPage > 1) { %>
    <a href="browse.jsp?page=<%= currentPage - 1 %>">上一页</a>
    <% } %>

    <% for (int i = 1; i <= pageNumber; i++) { %>
    <% if (i == currentPage) { %>
    <a class="current" href="browse.jsp?page=<%= i %>"><%= i %></a>
    <% } else { %>
    <a href="browse.jsp?page=<%= i %>"><%= i %></a>
    <% } %>
    <% } %>

    <% if (currentPage < pageNumber) { %>
    <a href="browse.jsp?page=<%= currentPage + 1 %>">下一页</a>
    <% } %>
</div>--%>

</body>
</html>