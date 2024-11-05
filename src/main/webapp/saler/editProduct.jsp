<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sửa sản phẩm</title>
    <link rel="stylesheet" href="path/to/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"  crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Sửa sản phẩm</h1>
    <form action="saler" method="post">
        <input type="hidden" name="action" value="updateProduct">
        <input type="hidden" name="id" value="${product.productID}">
        <div class="form-group">
            <label for="name">Tên sản phẩm:</label>
            <input type="text" class="form-control" name="name" value="${product.productName}" required>
        </div>
        <div class="form-group">
            <label for="quality">Chất lượng:</label>
            <input type="number" class="form-control" name="quality" value="${product.quality}" required>
        </div>
        <div class="form-group">
            <label for="price">Giá:</label>
            <input type="number" class="form-control" name="price" step="0.01" value="${product.price}" required>
        </div>
        <div class="form-group">
            <label for="discount">Giảm giá:</label>
            <input type="number" class="form-control" name="discount" step="0.01" value="${product.discount}">
        </div>
        <div class="form-group">
            <label for="imagePath">Hình ảnh:</label>
            <input type="text" class="form-control" name="imagePath" value="${product.productImagePath}" required>
        </div>
        <div class="form-group">
            <label for="color">Màu sắc:</label>
            <input type="text" class="form-control" name="color" value="${product.color}">
        </div>
        <div class="form-group">
            <label for="style">Phong cách:</label>
            <input type="text" class="form-control" name="style" value="${product.style}">
        </div>
        <div class="form-group">
            <label for="categoryID">Danh mục:</label>
            <select class="form-control" name="categoryID">
                <c:forEach var="category" items="${categories}">
                    <option value="${category.categoryID}" <c:if test="${category.categoryID == product.categoryID}">selected</c:if>>${category.categoryName}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Cập nhật sản phẩm</button>
        <a href="saler?action=myProducts" class="btn btn-secondary">Trở về</a>
    </form>
</div>
<script src="path/to/bootstrap.bundle.min.js"></script>
</body>
</html>
