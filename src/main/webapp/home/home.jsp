<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ</title>
    
    <!-- Link Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"  crossorigin="anonymous">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"  crossorigin="anonymous">

    <!-- Custom CSS (nếu có) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <style>
        /* Tùy chỉnh màu nền cho navbar */
        .navbar {
            background-color: #007bff;
        }
        .navbar .navbar-brand, .navbar .nav-link, .navbar .nav-link i {
            color: #fff !important;
        }
        .navbar .nav-link:hover {
            color: #f8f9fa !important;
        }

        /* Đặt khoảng cách và bố cục cho card sản phẩm */
        .card {
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
        }
        .card-text {
            color: #28a745;
            font-weight: bold;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        /* CSS tùy chỉnh để ẩn dropdown khi không mở */
		.dropdown-menu {
		    display: none;
		    opacity: 0;
		    transition: opacity 0.3s ease;
		}
		
		.dropdown-menu.show {
		    display: block;
		    opacity: 1;
		}
        
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">Logo</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/home">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/about">Giới thiệu</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/contact">Liên hệ</a>
                </li>
            </ul>
            <ul class="navbar-nav">
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
            <script type="text/javascript">
	         // Xử lý khi nhấn vào tên tài khoản để hiển thị dropdown
	            document.getElementById("navbarDropdown").addEventListener("click", function(event) {
	                event.preventDefault(); // Ngăn chặn việc link tải lại trang
	                const dropdownMenu = this.nextElementSibling; // Lấy phần tử dropdown menu
	                
	                // Toggle class 'show' để ẩn/hiện menu
	                dropdownMenu.classList.toggle("show");
	            });
	
	            // Ẩn dropdown khi click bên ngoài
	            document.addEventListener("click", function(event) {
	                const isClickInsideDropdown = document.getElementById("navbarDropdown").contains(event.target);
	                const dropdownMenu = document.getElementById("navbarDropdown").nextElementSibling;
	                
	                // Kiểm tra nếu click ngoài dropdown thì ẩn dropdown đi
	                if (!isClickInsideDropdown) {
	                    dropdownMenu.classList.remove("show");
	                }
	            });
            </script>
        </div>
    </div>
</nav>


<div class="container-fluid">
    <div class="row">
        <!-- Cột bên trái: Danh sách danh mục -->
        <div class="col-md-3">
            <h3>Danh mục</h3>
            <ul class="list-group">
                <li class="list-group-item">
                    <button id="btnAllProducts" class="btn btn-primary btn-sm w-100">Tất cả sản phẩm</button>
                </li>
                <c:forEach items="${categories}" var="category">
                    <li class="list-group-item">
                        <input type="checkbox" class="category-checkbox" value="${category.categoryID}" id="category_${category.categoryID}" />
                        <label for="category_${category.categoryID}">${category.categoryName}</label>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <!-- Phần giữa: Danh sách sản phẩm -->
        <div class="col-md-6" id="product-list">
            <h3>Danh sách sản phẩm</h3>
            <c:if test="${not empty products}">
	            <div class="row">
	                <c:forEach items="${products}" var="product">
	                    <div class="col-md-4 mb-4">
	                        <div class="card">
	                            <img src="${product.productImagePath != null ? product.productImagePath : '/images/default.jpg'}"
	                                 class="card-img-top" alt="${product.productName}" style="height: 150px; object-fit: cover;">
	                            <div class="card-body">
	                                <h5 class="card-title">${product.productName}</h5>
	                                <p class="card-text">Giá: ${product.price}</p>
	                                <a href="${pageContext.request.contextPath}/productDetails?productId=${product.productID}" class="btn btn-primary btn-sm">Chi tiết</a>
	                                <a href="${pageContext.request.contextPath}/addToCart?productId=${product.productID}" class="btn btn-success btn-sm">Thêm vào giỏ</a>
	                                <a href="${pageContext.request.contextPath}/buyNow?productId=${product.productID}" class="btn btn-danger btn-sm">Mua ngay</a>
	                            </div>
	                        </div>
	                    </div>
	                </c:forEach>
	            </div>
            </c:if>
			<c:if test="${empty products}">
			    <p>Không có sản phẩm nào để hiển thị.</p>
			</c:if>
        </div>

        <!-- Cột bên phải: Thông tin chi tiết sản phẩm -->
        <div class="col-md-3" style="position: sticky; top: 0;">
            <h3>Chi tiết sản phẩm</h3>
            <c:if test="${not empty productDetail}">
                <div class="card">
                    <img src="${productDetail.productImagePath != null ? productDetail.productImagePath : '/images/default.jpg'}"
                         class="card-img-top" alt="${productDetail.productName}" style="height: 200px; object-fit: cover;">
                    <div class="card-body">
                        <h4>${productDetail.productName}</h4>
                        <p>Giá: ${productDetail.price}</p>
                        <p>Màu sắc: ${productDetail.color}</p>
                        <p>Kiểu dáng: ${productDetail.style}</p>
                        <p>Giảm giá: ${productDetail.discount}%</p>
                        <p>Số lượng còn: ${productDetail.quality}</p>
                        <p>Danh mục: ${productDetail.category.categoryName}</p>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty productDetail}">
                <p>Chọn sản phẩm để xem chi tiết.</p>
            </c:if>
        </div>
    </div>
</div>

<script>
    // Xử lý khi nhấn nút "Tất cả sản phẩm"
    document.getElementById('btnAllProducts').addEventListener('click', function() {
        $('.category-checkbox').prop('checked', false);
        loadProducts([]); // Gọi AJAX để hiển thị tất cả sản phẩm
    });

    // Hàm AJAX để load sản phẩm dựa trên danh mục
    function loadProducts(categoryIds) {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/filterProducts",
            data: { categoryIds: categoryIds.join(",") },
            success: function (data) {
                $('#product-list').html(data); // Cập nhật danh sách sản phẩm
            },
            error: function (xhr, status, error) {
                console.error("Error loading products: " + error);
            }
        });
    }

    // Xử lý sự kiện khi checkbox thay đổi
    $('.category-checkbox').change(function() {
        const selectedCategories = $('.category-checkbox:checked').map(function() {
            return this.value;
        }).get();

        loadProducts(selectedCategories); // Gọi AJAX với danh sách đã chọn
    });
</script>
<%@ include file="/homeLayout/footer.jsp" %>
