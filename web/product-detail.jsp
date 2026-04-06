<%@ page import="models.Product" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.ArrayList" %>

<%
    // L?y s?n ph?m t? servlet
    Product selected = (Product) request.getAttribute("product");
    if(selected == null){
        out.println("<h2 style='color:red;text-align:center;margin-top:50px;'>S?n ph?m không t?n t?i!</h2>");
        return;
    }

    // L?y danh sách ?nh
    ArrayList<String> images = selected.getImageUrls();
    if(images == null || images.isEmpty()){
        images = new ArrayList<>();
        images.add("default-image.jpg"); // n?u không có ?nh nào
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= selected.getProductName() %></title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Roboto', sans-serif;
            background-color: #1f293a;
            color: #fff;
        }

        .product-detail-container {
            width: 100vw;
            height: 100vh;
            display: flex;
            flex-direction: column;
            overflow-y: auto;
        }

        .product-images {
            width: 100%;
            flex: 1;
            background-color: #111827;
        }

        .product-images img {
            width: 100%;
            max-height: 80vh;
            object-fit: contain;
        }

        .product-info {
            padding: 20px 40px;
            background-color: #2c4766;
        }

        .product-info h1 {
            font-size: 2.5em;
            color: #0ef;
            margin-bottom: 15px;
        }

        .product-info .price {
            font-size: 2em;
            color: #0ef;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .product-info .description {
            font-size: 1.2em;
            line-height: 1.6;
            margin-bottom: 25px;
        }

        .back-btn {
            display: inline-block;
            padding: 12px 30px;
            border-radius: 30px;
            background-color: #0ef;
            color: #1f293a;
            font-weight: 600;
            text-decoration: none;
            transition: 0.3s ease;
        }

        .back-btn:hover {
            background-color: #1f293a;
            color: #0ef;
            border: 2px solid #0ef;
        }
    </style>
</head>
<body>
    <div class="product-detail-container">

        <!-- Carousel hi?n th? t?t c? ?nh -->
        <div id="productCarousel" class="carousel slide product-images" data-bs-ride="carousel">
            <div class="carousel-inner">
                <% for(int i=0; i<images.size(); i++) { %>
                    <div class="carousel-item <%= (i==0?"active":"") %>">
                        <img src="<%= images.get(i) %>" alt="Product Image <%= i+1 %>">
                    </div>
                <% } %>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#productCarousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#productCarousel" data-bs-slide="next">
                <span class="carousel-control-next-icon"></span>
            </button>
        </div>

        <!-- Thông tin s?n ph?m -->
        <div class="product-info">
            <h1><b><%= selected.getProductName() %></b></h1>
            <div class="price">Giá: <%= new DecimalFormat("#,###").format(selected.getUnitPrice()) %> $</div>
            <div class="description"><%= selected.getDescription() %></div>
            <a href="home" class="back-btn">Go Back</a>
        </div>

    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>