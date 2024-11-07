<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thêm sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Thêm sản phẩm mới</h1>
 <form action="saler?action=saveProduct" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="saveProduct">
    <div class="form-group">
        <label for="name">Tên sản phẩm:</label>
        <input type="text" class="form-control" name="productName" required>
    </div>
    <div class="form-group">
        <label for="quality">Số lượng:</label>
        <input type="number" class="form-control" name="quality">
    </div>
    <div class="form-group">
        <label for="price">Giá:</label>
        <input type="number" class="form-control" name="price" step="0.01" required>
    </div>
    <div class="form-group">
        <label for="discount">Giảm giá:</label>
        <input type="number" class="form-control" name="discount" step="0.01">
    </div>
    <div class="form-group">
        <label for="productImagePath">Hình ảnh:</label>
        <input type="file" class="form-control" name="productImagePath" accept="image/*" required>
    </div>
    <div class="form-group">
        <label for="color">Màu sắc:</label>
        <input type="text" class="form-control" name="color">
    </div>
    <div class="form-group">
        <label for="style">Phong cách:</label>
        <input type="text" class="form-control" name="style">
    </div>
    <div class="form-group">
        <label for="categoryID">Danh mục:</label>
        <select class="form-control" name="categoryName" id="categoryID" required>
            <c:forEach var="category" items="${categories}">
                <option value="${category.categoryName}">${category.categoryName}</option> <!-- Giá trị của option là categoryName -->
            </c:forEach>
            <c:if test="${empty categories}">
                <option value="">Không có danh mục nào!</option>
            </c:if>
        </select>
        <a class="btn btn-primary mt-3" href="saler?action=createCategory">Thêm danh mục</a>
    </div>
    <button type="submit" class="btn btn-success mt-3">Lưu sản phẩm</button>
    <a href="saler?action=myProducts" class="btn btn-secondary mt-3">Trở về</a>
</form>


    <c:if test="${not empty error}">
    <p class="text-danger">${error}</p>
	</c:if>
	<c:if test="${not empty error1}">
	    <p class="text-danger">${error1}</p>
	</c:if>
	<c:if test="${not empty error3}">
	    <p class="text-danger">${error3}</p>
	</c:if>
	<c:if test="${not empty error4}">
	    <p class="text-danger">${error4}</p>
	</c:if>
	<c:if test="${not empty error5}">
	    <p class="text-danger">${error5}</p>
	</c:if>
	<c:if test="${not empty error6}">
	    <p class="text-danger">${error6}</p>
	</c:if>
	<c:if test="${not empty error7}">
	    <p class="text-danger">${error7}</p>
	</c:if>
	<c:if test="${not empty error8}">
	    <p class="text-danger">${error8}</p>
	</c:if>
</div>
</body>
</html>
