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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
	

	    .list-group-item {
	        position: relative;
	        cursor: pointer;
	    }
	
	    .list-group-item input[type="checkbox"] {
	        position: relative;
	        z-index: 2;
	    }
	
	    .list-group-item label {
	        cursor: pointer;
	        z-index: 1;
	    }
	
	    /* Hover effect for better UX */
	    .list-group-item:hover {
	        background-color: #f8f9fa;
	    }


		
        
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Logo</a>
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
	            <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cart">
                        <i class="fa fa-shopping-cart"></i> Giỏ hàng
                    </a>
                </li>
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
							    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile">Cá nhân</a></li>
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
				   <%-- <li class="list-group-item" style="cursor: pointer;">
				        <a class="chose-cate text-dark" href="home?id=${category.categoryID}" style="text-decoration: none; cursor: pointer;">
				            <input type="checkbox" class="category-checkbox me-2" value="${category.categoryID}" id="category_${category.categoryID}" />
				            <label for="category_${category.categoryID}" style="cursor: pointer;">${category.categoryName}</label>
				        </a>
				    </li>  --%>
				    <li class="list-group-item d-flex align-items-center" style="cursor: pointer;">
			            <input type="checkbox" class="category-checkbox me-2 flex-shrink-0" value="${category.categoryID}" id="category_${category.categoryID}" />
			            <label for="category_${category.categoryID}" class="w-100 mb-0">${category.categoryName}</label>
			        </li>
									    
				    <%-- <a href="home?id=${category.categoryID}" class="list-group-item list-group-item-action list-group-item-success 
            	${tag == c.id ? "active" : ""}">${category.categoryName}</a> --%>
				</c:forEach>

            </ul>
        </div>

        <!-- Phần giữa: Danh sách sản phẩm -->
        <div class="col-md-7" id="product-list">
            <h3>Danh sách sản phẩm</h3>
           <%--  <%@ include file="productListHome.jsp" %> --%>
            <div class="row">
    <c:if test="${not empty products}">
        <c:forEach items="${products}" var="product">
            <div class="col-md-4 mb-4 product-card" data-id="${product.productID}">
                <div class="card">
	                <a href="${pageContext.request.contextPath}/product-detail?productId=${product.productID}" style="cursor: pointer; text-decoration: none;">
	                    <img src="${product.productImagePath != null ? product.productImagePath : '/images/default.jpg'}"
	                         class="card-img-top" alt="${product.productName}" style="height: 150px; object-fit: cover;">
                   </a> 
                    <div class="card-body">
                        <h5 class="card-title">${product.productName}</h5>
                        <p class="card-text">Giá: ${product.price}</p>
                        <p></p>
                        <a href="${pageContext.request.contextPath}/product-detail?productId=${product.productID}" class="btn btn-primary btn-sm">Chi tiết</a>
                        <form action="${pageContext.request.contextPath}/add-to-cart" method="POST">
						    <input type="hidden" name="productId" value="${product.productID}">
						    <input type="hidden" name="productName" value="${product.productName}">
						    <input type="hidden" name="quantity" value="1"> <!-- Giả sử bạn thêm 1 sản phẩm -->
						    <input type="hidden" name="price" value="${product.price}">
						    <button type="submit" class="btn btn-success btn-sm">Thêm vào giỏ</button>
						</form>

                        <a href="${pageContext.request.contextPath}/buyNow?productId=${product.productID}" class="btn btn-danger btn-sm">Mua ngay</a>
                    </div>
                </div>
                
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty products}">
        <p>Không có sản phẩm nào để hiển thị.</p>
    </c:if>
</div>
            
			
        </div>

        <!-- Cột bên phải: Thông tin chi tiết sản phẩm -->
        <%-- <div class="col-md-3" style="position: sticky; top: 0;" id="product-detail">
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
        </div> --%>
    </div>
</div>

<script>


	//Xử lý khi checkbox thay đổi
	$('.category-checkbox').change(function () {
        const selectedCategoryIds = $('.category-checkbox:checked')
            .map(function () {
                return $(this).val();
            })
            .get();

        if (selectedCategoryIds.length === 0) {
            // Nếu không có danh mục nào được chọn, lấy tất cả sản phẩm
            loadProducts();
        } else {
            // Gửi danh sách ID danh mục được chọn
            loadProducts(selectedCategoryIds);
        }
    });

    // Hàm loadProducts
    function loadProducts(categoryIds = null) {
        $.ajax({
            url: '${pageContext.request.contextPath}/home', // Thay đường dẫn servlet của bạn
            type: 'GET',
            data: categoryIds ? { id: categoryIds } : {}, // Nếu không có ID, gửi yêu cầu rỗng
            success: function (response) {
                $('#product-list').html(response); // Hiển thị kết quả trong vùng sản phẩm
            },
            error: function () {
                alert('Có lỗi xảy ra!');
            }
        });
    }
	
	// Hàm AJAX để load sản phẩm
	function loadProducts(categoryIds) {
	    $.ajax({
	        type: "GET",
	        url: "${pageContext.request.contextPath}/home",
	        traditional: true, // Quan trọng để gửi danh sách (Array)
	        data: { id: categoryIds }, // Gửi danh sách ID
	        success: function (data) {
	            $('#product-list').html($(data).find('#product-list').html());
	        },
	        error: function (xhr, status, error) {
	            console.error("Error loading products: " + error);
	        }
	    });
	}
	//Khi nhấn nút "Tất cả sản phẩm", bỏ chọn tất cả checkbox và hiển thị tất cả sản phẩm
	$('#btnAllProducts').click(function () {
	    $('.category-checkbox').prop('checked', false);
	    loadProducts(null);
	});
</script>
<%@ include file="/homeLayout/footer.jsp" %>
