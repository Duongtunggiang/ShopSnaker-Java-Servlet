<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thêm danh mục</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Thêm danh mục mới</h1>
    <form action="saler" method="post">
        <input type="hidden" name="action" value="saveCategory">
        <div class="form-group">
            <label for="name">Tên danh mục:</label>
            <input type="text" class="form-control" name="name" required>
        </div>
        <div class="form-group">
            <label for="brandImagePath">Đường dẫn hình ảnh:</label>
            <input  class="form-control" type="file" name="brandImagePath" required>
        </div>
        <button type="submit" class="btn btn-success">Lưu danh mục</button>
    </form>
    <a href="saler?action=createProduct" class="btn btn-secondary">Trở về</a>
</div>
<script src="path/to/bootstrap.bundle.min.js"></script>
</body>
</html>
