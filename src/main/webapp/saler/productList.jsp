<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
    <link rel="stylesheet" href="path/to/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"  crossorigin="anonymous">
    
     <!-- Đường dẫn tới Bootstrap -->
</head>
<body>
<div class="container">
    <h1>Danh sách sản phẩm</h1>
    <a href="saler?action=salerHome" class="text-link p-3">&lt;Trở lại</a>
    <a href="saler?action=createProduct" class="btn btn-primary">Thêm sản phẩm mới</a>
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên sản phẩm</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Ảnh</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
        <c:if test="${not empty products}">
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.productID}</td>
                    <td>${product.productName}</td>
                    <td>${product.price}</td>
                    <td>${product.quality}</td>
                    <td><img src="${pageContext.request.contextPath}/${product.productImagePath}" alt="Product Image">
                    </td>
                    <td>
                        <a href="saler?action=editProduct&id=${product.productID}" class="btn btn-warning">Sửa</a>
                        <form action="saler" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="deleteProduct">
                            <input type="hidden" name="id" value="${product.productID}">
                            <button type="submit" class="btn btn-danger">Xóa</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </c:if>
			<c:if test="${empty products}">
			    <p>Không có sản phẩm nào để hiển thị.</p>
			</c:if>
        </tbody>
    </table>
</div>
<script src="path/to/bootstrap.bundle.min.js"></script> <!-- Đường dẫn tới Bootstrap JS -->
</body>
</html>
