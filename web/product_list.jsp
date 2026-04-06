<%@page import="models.User"%>
<%@page import="models.Product"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.text.DecimalFormat" %>

<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách sản phẩm</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <style>
            * {
                box-sizing: border-box;
                font-family: Arial, sans-serif;
            }
            body {
                margin: 0;
                padding: 20px;
                background-color: #f5f5f5;
            }
            .container {
                max-width: 1200px;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                margin-top: 60px !important; 
            }
            h1 {
                text-align: center;
                color: #333;
            }
            .controls {
                display: flex;
                justify-content: space-between;
                margin-bottom: 20px;
                flex-wrap: wrap;
                gap: 10px;
            }
            .search-form {
                flex: 1;
                display: flex;
                max-width: 500px;
                min-width: 200px;
            }
            .search-form input {
                flex: 1;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px 0 0 4px;
            }
            .search-form button {
                padding: 10px 15px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 0 4px 4px 0;
                cursor: pointer;
            }
            .sort-form {
                display: flex;
                align-items: center;
                gap: 10px;
            }
            .sort-form label {
                font-weight: bold;
            }
            .sort-form select {
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            .sort-form button {
                padding: 10px 15px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .product-grid {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
                gap: 20px;
            }
            .product-card {
                border: 1px solid #ddd;
                border-radius: 8px;
                overflow: hidden;
                transition: transform 0.3s ease;
                background-color: white;
            }
            .product-card:hover {
                transform: translateY(-5px);
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            }
            .product-image {
                height: 200px;
                background-color: #f0f0f0;
                display: flex;
                align-items: center;
                justify-content: center;
                color: #666;
                background-size: cover;
                background-position: center;
            }
            .product-info {
                padding: 15px;
            }
            .product-title {
                font-weight: bold;
                margin-bottom: 8px;
                font-size: 16px;
            }
            .product-price {
                color: #e91e63;
                font-weight: bold;
                font-size: 18px;
            }
            .product-description {
                color: #666;
                font-size: 14px;
                margin-top: 8px;
            }
            .view-details {
                display: inline-block;
                margin-top: 10px;
                background-color: #4CAF50;
                color: white;
                text-decoration: none;
                padding: 8px 12px;
                border-radius: 4px;
                font-size: 14px;
            }
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 30px;
                gap: 5px;
            }
            .pagination a {
                padding: 8px 15px;
                border: 1px solid #ddd;
                background-color: white;
                text-decoration: none;
                color: #333;
                border-radius: 4px;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border-color: #4CAF50;
            }
            .pagination a:hover:not(.active) {
                background-color: #f1f1f1;
            }
            .no-results {
                text-align: center;
                padding: 20px;
                grid-column: 1 / -1;
            }
            .product-details {
                border: 1px solid #ddd;
                border-radius: 8px;
                padding: 20px;
                margin-top: 20px;
                background-color: #f9f9f9;
            }
            .product-details h2 {
                margin-top: 0;
                color: #333;
            }
            .back-button {
                display: inline-block;
                margin-top: 10px;
                background-color: #333;
                color: white;
                text-decoration: none;
                padding: 8px 12px;
                border-radius: 4px;
                font-size: 14px;
            }
            @media (max-width: 768px) {
                .controls {
                    flex-direction: column;
                }
                .search-form {
                    max-width: 100%;
                }
                .product-grid {
                    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
                }
            }
            .product-carousel {
            height: 200px;
            overflow: hidden;
            }

            .product-img {
                height: 200px;
                object-fit: cover;
            }

            .carousel-control-prev-icon,
            .carousel-control-next-icon {
                background-color: rgba(0,0,0,0.5);
                border-radius: 50%;
                padding: 10px;
            }
            
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark" style="position: fixed; z-index: 1000; left: 0; right: 0; top: 0">
            <a class="navbar-brand" href="#"><b>NPS</b></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Chat</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <ul class="navbar-nav mr-auto">
                        <% User user = (User)session.getAttribute("user"); %>
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Xin chào <%= user.getFirstName() %> <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Đăng xuất</a>
                        </li>
                    </ul>
                </form>
            </div>
        </nav>
        <div class="container">
            <h1>Danh sách sản phẩm</h1>

            <div class="controls">
                <!-- Form tìm kiếm -->
                <form class="search-form" action="home" method="get">
                    <input type="text" name="keyword" placeholder="Tìm kiếm sản phẩm..." value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : "" %>">
                    
                    <!-- giữ sort -->
                    <input type="hidden" name="sort"
                            value="<%= request.getAttribute("sort") != null ? request.getAttribute("sort") : "" %>">
                    
<!--                     reset về page 1 khi sort 
                    <input type="hidden" name="page" value="1">-->
                    
                    <button type="submit">Tìm</button>
                </form>

                <!-- Form sắp xếp -->
                <form class="sort-form" action="home" method="get">
                    <label for="sort">Sắp xếp:</label>
                    <%
                        String sort = request.getParameter("sort");
                        if (sort == null) sort = "default"; // mặc định
                    %>
                    <select id="sort" name="sort">
                        <option value="default" <%= "default".equals(sort) ? "selected" : "" %>>Mặc định</option>
                        <option value="newest" <%= "newest".equals(sort) ? "selected" : "" %>>Mới nhất</option>
                        <option value="name-asc" <%= "name-asc".equals(sort) ? "selected" : "" %>>Tên: A-Z</option>
                        <option value="name-desc" <%= "name-desc".equals(sort) ? "selected" : "" %>>Tên: Z-A</option>
                        <option value="price-asc" <%= "price-asc".equals(sort) ? "selected" : "" %>>Giá: Thấp đến cao</option>
                        <option value="price-desc" <%= "price-desc".equals(sort) ? "selected" : "" %>>Giá: Cao đến thấp</option>
                    </select>
                    
                    <!-- giữ keyword -->
                    <input type="hidden" name="keyword"
                            value="<%= request.getAttribute("keyword") != null ? request.getAttribute("keyword") : "" %>">
                    
<!--                     reset về page 1 khi sort 
                    <input type="hidden" name="page" value="1">-->
                    <button type="submit">Áp dụng</button>
                </form>
            </div>

            <!-- Danh sách sản phẩm -->
            <div class="product-grid">
                <% ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products"); %>
                <% if (products != null && !products.isEmpty()) {%>
                <% for(Product p : products) { %>
                <div class="product-card">
                   <div id="carousel-<%= p.getId() %>" 
                        class="carousel slide product-carousel" 
                        data-ride="carousel" 
                        data-interval="3000">

                <div class="carousel-inner">
                    <% 
                        ArrayList<String> images = p.getImageUrls();
                        for(int i = 0; i < images.size(); i++) { 
                    %>
                        <div class="carousel-item <%= (i == 0 ? "active" : "") %>">
                            <img class="d-block w-100 product-img" src="<%= images.get(i) %>">
                        </div>
                    <% } %>
                </div>

                <!-- Nút trái -->
                <a class="carousel-control-prev" href="#carousel-<%= p.getId() %>" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>

                <!-- Nút phải -->
                <a class="carousel-control-next" href="#carousel-<%= p.getId() %>" role="button" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>

            </div>
                    <div class="product-info">
                        <div class="product-title"><%= p.getProductName() %></div>
                        <div class="product-price"><%= new DecimalFormat("#,###").format(p.getUnitPrice()) %> đ</div>
                        <div class="product-description">
                            <%= p.getDescription() %>
                        </div>
                        <a href="product-detail?id=<%= p.getId() %>" class="view-details">Xem chi tiết</a>
                    </div>
                </div>
                <% } 
                } else {
                %>
                    <p>Không tìm thấy sản phẩm nào!</p>
                <%
                    }
                %>
                
            </div>

            <!-- Phân trang -->
            <div class="pagination">
                <%
                    int currentPage = (Integer) request.getAttribute("currentPage");
                    int totalPages = (Integer) request.getAttribute("totalPages");
                    int pageSize = (Integer) request.getAttribute("pageSize");
                    String keyword = (String) request.getAttribute("keyword");
                    String sort2 = (String) request.getAttribute("sort");
                %>
                <% for (int i = 1; i<= totalPages; i++) { %>
                    <a href="home?page=<%= i %>&pageSize=<%= pageSize %>&keyword=<%= keyword != null ? keyword : "" %>&sort=<%= sort2 != null ? sort2 : "" %>"
                       class="<%= (i == currentPage ? "active" : "") %>">
                        <%= i %>
                    </a>
                <% } %>
            </div>
        </div>
    </body>
</html>