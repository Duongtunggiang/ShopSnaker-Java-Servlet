<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm: ${product.productName}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container my-4">
        <h1 class="mb-4 text-center">Chi tiết sản phẩm: ${product.productName}</h1>

        <form action="saler?action=updateProduct" method="post" enctype="multipart/form-data">
            <input type="hidden" name="productId" value="${product.productID}"/>

            <div class="mb-3">
                <label for="productName" class="form-label">Tên sản phẩm</label>
                <input type="text" class="form-control" id="productName" name="productName" value="${product.productName}" required/>
            </div>

            <div class="mb-3">
                <label for="productPrice" class="form-label">Giá</label>
                <input type="number" class="form-control" id="productPrice" name="price" value="${product.price}" required/>
            </div>

            <div class="mb-3">
                <label for="productQuality" class="form-label">Số lượng</label>
                <input type="number" class="form-control" id="productQuality" name="quality" value="${product.quality}" required/>
            </div>

            <div class="mb-3">
                <label for="productCategory" class="form-label">Danh mục</label>
                <input type="text" class="form-control" id="productCategory" name="categoryName" value="${product.categoryName}" required/>
            </div>

            <div class="mb-3">
                <label for="productStatus" class="form-label">Trạng thái</label>
                <select class="form-select" name="status" id="productStatus" required>
                    <option value="Có thể bán" ${product.status == 'Có thể bán' ? 'selected' : ''}>Có thể bán</option>
                    <option value="Chưa thể bán" ${product.status == 'Chưa thể bán' ? 'selected' : ''}>Chưa thể bán</option>
                    <option value="Tạm dừng" ${product.status == 'Tạm dừng' ? 'selected' : ''}>Tạm dừng</option>
                </select>
            </div>

            <!-- Hình ảnh sản phẩm -->
            <div class="mb-3">
                <label class="form-label">Hình ảnh sản phẩm</label>
                <input type="file" class="form-control" name="productImage"/>
                <img src="${pageContext.request.contextPath}/${product.productImagePath}" alt="Product Image" style="height: 150px; object-fit: cover;" />
            </div>

            <!-- Các thông tin khác -->
            <div class="mb-3">
                <h4>Kích thước sản phẩm</h4>
                <c:forEach var="size" items="${sizes}">
                    <input type="text" class="form-control" name="size" value="${size.sizeValue}" />
                </c:forEach>
                <a href="#" class="btn btn-primary">Thêm kích thước</a>
            </div>

            <div class="mb-3">
                <h4>Màu sắc sản phẩm</h4>
                <c:forEach var="color" items="${colors}">
                    <input type="text" class="form-control" name="color" value="${color.colorValue}" />
                </c:forEach>
                <a href="#" class="btn btn-primary">Thêm màu sắc</a>
            </div>

            <div class="mb-3">
                <h4>Quà tặng kèm theo</h4>
                <c:forEach var="gift" items="${gifts}">
                    <input type="text" class="form-control" name="gift" value="${gift.giftName}" />
                </c:forEach>
                <a href="#" class="btn btn-primary">Thêm quà tặng</a>
            </div>

            <button type="submit" class="btn btn-success">Cập nhật</button>
        </form>

        <a href="${pageContext.request.contextPath}/saler?action=myProducts" class="btn btn-primary">Quay lại danh sách sản phẩm</a>
    </div>
</body>
</html>
