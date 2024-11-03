<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Tin Cá Nhân</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Thông Tin Cá Nhân</h2>
        <div class="card">
            <div class="card-body">
                <p><strong>Tên đăng nhập:</strong> ${account.username}</p>
                <p><strong>Vai trò:</strong> 
                    <c:choose>
                        <c:when test="${account.roleID == 1}">Admin</c:when>
                        <c:when test="${account.roleID == 2}">Saler</c:when>
                        <c:when test="${account.roleID == 3}">Customer</c:when>
                    </c:choose>
                </p>
                <a href="logout" class="btn btn-secondary">Đăng Xuất</a>
            </div>
        </div>
    </div>
</body>
</html>
