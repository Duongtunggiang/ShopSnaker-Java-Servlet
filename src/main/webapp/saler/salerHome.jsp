<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ - Ứng Dụng Saler</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <style>
        .dropdown-menu-custom {
            display: none;
            position: absolute;
            background-color: #ffffff;
            min-width: 160px;
            z-index: 1000;
        }
        .dropdown-menu-custom a {
            display: block;
            padding: 10px;
            color: #333;
            text-decoration: none;
        }
        .dropdown-menu-custom a:hover {
            background-color: #f1f1f1;
        }
        .hero-section {
            background-color: #f8f9fa;
            padding: 40px;
            text-align: center;
        }
        .product-card {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 20px;
            text-align: center;
        }
        .product-card img {
            max-width: 100%;
            height: 200px;
            object-fit: cover;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div class="container">
            <a href="saler?action=myProducts" class="navbar-brand">Ứng Dụng Saler</a>
            <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target=".navbar-collapse" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse d-sm-inline-flex justify-content-between">
                <ul class="navbar-nav flex-grow-1">
                    <li class="nav-item">
                        <a href="saler?action=myProducts" class="nav-link">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                        <a href="saler?action=myProducts" class="nav-link">Sản phẩm của tôi</a>
                    </li>
                    <li class="nav-item">
                        <a href="saler?action=orders" class="nav-link">Đơn hàng</a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <c:choose>
					    <c:when test="${empty sessionScope.username}">
					        <a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">Tài khoản
					            <i class="fa fa-user" title="Cá nhân"></i>
					        </a>
					    </c:when>
					    <c:otherwise>
					        <!-- Dropdown khi đã đăng nhập -->
					        <li class="nav-item dropdown">
					            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
					                <span id="username">${sessionScope.username}</span>
					                <i class="fa fa-user"></i>
					            </a>
					            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
					                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/infor-customer?user=${sessionScope.username}">Cá nhân</a></li>
					                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/my-wallet">Ví của tôi</a></li>
					                <li><hr class="dropdown-divider"></li>
					                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
					            </ul>
					        </li>
					    </c:otherwise>
					</c:choose>

                </ul>
            </div>
        </div>
    </nav>
    
    <!-- Nội dung trang chủ -->
    <div class="container body-content">
        <!-- Hero Section -->
        <div class="hero-section">
            <h1>Chào mừng đến với Ứng Dụng Saler</h1>
            <p>Quản lý sản phẩm và đơn hàng của bạn dễ dàng với các công cụ và tính năng mạnh mẽ.</p>
            <a href="saler?action=myProducts" class="btn btn-primary">Khám phá Sản phẩm của tôi</a>
            <a href="saler?action=orders" class="btn btn-secondary">Xem Đơn Hàng</a>
        </div>

        <!-- Sản phẩm nổi bật -->
        <div class="mt-5">
            <h2>Sản phẩm nổi bật</h2>
            <div class="row">
                <%-- Giả sử đây là các sản phẩm được lấy từ database, bạn cần thêm logic backend để hiển thị sản phẩm thật --%>
                <div class="col-md-4">
                    <div class="product-card">
                        <img src="images/sample1.jpg" alt="Sản phẩm 1">
                        <h5>Sản phẩm 1</h5>
                        <p>Giá: 100.000 VND</p>
                        <a href="saler?action=viewProduct&productId=1" class="btn btn-outline-primary">Xem chi tiết</a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="product-card">
                        <img src="images/sample2.jpg" alt="Sản phẩm 2">
                        <h5>Sản phẩm 2</h5>
                        <p>Giá: 200.000 VND</p>
                        <a href="saler?action=viewProduct&productId=2" class="btn btn-outline-primary">Xem chi tiết</a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="product-card">
                        <img src="images/sample3.jpg" alt="Sản phẩm 3">
                        <h5>Sản phẩm 3</h5>
                        <p>Giá: 300.000 VND</p>
                        <a href="saler?action=viewProduct&productId=3" class="btn btn-outline-primary">Xem chi tiết</a>
                    </div>
                </div>
            </div>
        </div>

        <hr />
        <footer>
            <p>&copy; <%= java.time.Year.now() %> - Ứng Dụng Saler</p>
        </footer>
    </div>

    <!-- Bootstrap và jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#userMenuToggle").click(function (event) {
                event.preventDefault();
                $("#userDropdownMenu").toggle();
            });
            $(document).click(function (event) {
                if (!$(event.target).closest("#userMenuToggle, #userDropdownMenu").length) {
                    $("#userDropdownMenu").hide();
                }
            });
        });
    </script>
</body>
</html>
