<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .product-image {
            width: 100%;
            max-width: 500px;
            margin-bottom: 15px;
        }
        .product-detail-section {
            margin-bottom: 30px;
        }
        .product-detail-title {
            font-size: 1.5em;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container my-4">
        <h1 class="mb-4 text-center">Chi tiết sản phẩm</h1>

        <c:if test="${not empty product}">
            <!-- Thông tin sản phẩm -->
            <div class="product-detail-section">
                <h2 class="product-detail-title">${product.productName}</h2>
                <p><strong>Giá: </strong>${product.price}</p>
                <p><strong>Số lượng: </strong>${product.quality}</p>

                <div class="row">
                    <div class="col-md-6">
                        <h4>Hình ảnh sản phẩm</h4>
                        <img src="${pageContext.request.contextPath}/${product.productImagePath != null ? product.productImagePath : '/images/default.jpg'}"
	                         class="card-img-top" alt="${product.productName}" style="height: 150px; object-fit: cover;">
                        <c:if test="${not empty images}">
                            <c:forEach var="image" items="${images}">
                                <img src="${image.imagePath}" alt="Product Image" class="product-image">
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty images}">
                            <p>Chưa có hình ảnh bổ sung</p>
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        <h4>Chi tiết sản phẩm</h4>
                        <c:if test="${not empty productDetails}">
                            <c:forEach var="detail" items="${productDetails}">
                                <p>${detail.description}</p>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty productDetails}">
                            <p>Chưa có thông tin chi tiết</p>
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- Kích thước sản phẩm -->
            <div class="product-detail-section">
                <h4>Kích thước sản phẩm</h4>
                <c:if test="${not empty sizes}">
                    <ul>
                        <c:forEach var="size" items="${sizes}">
                            <li>${size.sizeValue}</li>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${empty sizes}">
                    <p>Chưa có thông tin kích thước</p>
                </c:if>
            </div>

            <!-- Màu sắc sản phẩm -->
            <div class="product-detail-section">
                <h4>Màu sắc sản phẩm</h4>
                <c:if test="${not empty colors}">
                    <ul>
                        <c:forEach var="color" items="${colors}">
                            <li>${color.colorValue}</li>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${empty colors}">
                    <p>Chưa có thông tin màu sắc</p>
                </c:if>
            </div>

            <!-- Quà tặng sản phẩm -->
            <div class="product-detail-section">
                <h4>Quà tặng kèm theo</h4>
                <c:if test="${not empty gifts}">
                    <ul>
                        <c:forEach var="gift" items="${gifts}">
                            <li>
                                <strong>${gift.giftName}</strong>: ${gift.description} (Số lượng: ${gift.quantity})
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${empty gifts}">
                    <p>Chưa có quà tặng</p>
                </c:if>
            </div>

            <!-- Bài viết liên quan -->
            <div class="product-detail-section">
                <h4>Bài viết liên quan</h4>
                <c:if test="${not empty relatedArticles}">
                    <ul>
                        <c:forEach var="article" items="${relatedArticles}">
                            <li>
                                <strong>${article.title}</strong>: ${article.content}
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${empty relatedArticles}">
                    <p>Chưa có bài viết liên quan</p>
                </c:if>
            </div>

        </c:if>

        <c:if test="${empty product}">
            <p>Sản phẩm không tồn tại</p>
        </c:if>

        <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Quay lại danh sách sản phẩm</a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
