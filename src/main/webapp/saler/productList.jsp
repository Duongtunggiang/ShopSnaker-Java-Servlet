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
            <th>Trạng thái</th>
            <th>Ảnh</th>
            <th>Danh mục</th>
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
                <td>
                    <c:if test="${product.status == 'Có thể bán'}">
                        <span class="text-success"><b>Có thể bán</b></span><a href="#" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#updateStatusModal" 
   onclick="setProductId(${product.productID})">Cập nhật</a>
                    </c:if>
                    <c:if test="${product.status == 'Chưa thể bán'}">
                        <span class="text-danger"><b>Chưa thể bán </b></span><a href="#" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#updateStatusModal" 
   onclick="setProductId(${product.productID})">Cập nhật</a>
                    </c:if>
                    <c:if test="${product.status == 'Tạm dừng'}">
                        <span class="text-warning"><b>Tạm dừng </b></span><a href="#" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#updateStatusModal" 
   onclick="setProductId(${product.productID})">Cập nhật</a>
                    </c:if>
                    <p>${error}</p>
                </td>
                <td><img src="${pageContext.request.contextPath}/${product.productImagePath}" alt="Product Image" style="height: 150px; object-fit: cover;"></td>
                <td>${product.categoryName}</td>
                
                <td><a href="${pageContext.request.contextPath}/saler-product-detail?productId=${product.productID}" class="btn btn-success m-2">Chi tiết</a>
                    <%-- <a href="saler?action=editProduct&id=${product.productID}" class="btn btn-warning">Sửa</a> --%>
                    <form action="saler?action=deleteProduct" method="post" style="display:inline;" onsubmit="return confirmDelete();">
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
	<c:if test="${not empty successMessage}">
	    <div class="alert alert-success" role="alert">
	        ${successMessage}
	    </div>
	</c:if>
	<c:if test="${not empty error}">
	    <div class="alert alert-danger">
	        ${error}
	    </div>
	</c:if>

<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger" role="alert">
        ${errorMessage}
    </div>
</c:if>



</div>
<script>
    function confirmDelete() {
        return confirm("Bạn có chắc chắn muốn xóa sản phẩm này không?");
    }
</script>
<!-- Modal -->
<div class="modal fade" id="updateStatusModal" tabindex="-1" aria-labelledby="updateStatusLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateStatusLabel">Cập nhật trạng thái</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="saler?action=updateStatus" method="post">
                <div class="modal-body">
                    <input type="hidden" id="productId" name="productId">
                    <div class="mb-3">
                        <label for="status" class="form-label">Trạng thái</label>
                        <select id="status" name="status" class="form-select">
                            <option value="Có thể bán">Có thể bán</option>
                            <option value="Chưa thể bán">Chưa thể bán</option>
                            <option value="Tạm dừng">Tạm dừng</option>
                        </select>
                    </div>
                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-warning">Cập nhật trạng thái</button>
                </div>
            </form>
        </div>
    </div>
</div>


<script>
    // Hàm để gán ID sản phẩm vào input ẩn trong modal
    function setProductId(id) {
        document.getElementById("productId").value = id;
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>

</body>
</html>
