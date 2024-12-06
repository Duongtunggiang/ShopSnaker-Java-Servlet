<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
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

    <div class="container mt-4">
        <h1>Giỏ hàng của bạn</h1>
        <c:choose>
            <c:when test="${empty cartItems}">
                <p class="text-center">Giỏ hàng của bạn đang trống.</p>
            </c:when>
            <c:otherwise>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Tên sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Giá</th>
                            <th>Thành tiền</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${cartItems}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${item.productName}</td>
                                <td>${item.quantity}</td>
                                <td>${item.price}</td>
                                <%-- <td>${item.price.multiply(new java.math.BigDecimal(item.quantity))}</td> --%>
                            	<td>${item.totalPrice}</td>
                            	<td>
                            	<a class="btn btn-success" href="#">Thanh toán</a>
                            		<form action="delete-from-cart" method="post">
							            <input type="hidden" name="cartItemId" value="${item.cartItemId}" />
							            <button type="submit" class="btn btn-danger">Xóa</button>
							        </form>
                            	</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
