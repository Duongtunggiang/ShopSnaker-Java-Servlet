<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
    <c:if test="${not empty products}">
        <c:forEach items="${products}" var="product">
            <div class="col-md-4 mb-4 product-card" data-id="${product.productID}">
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
    </c:if>
    <c:if test="${empty products}">
        <p>Không có sản phẩm nào để hiển thị.</p>
    </c:if>
</div>
