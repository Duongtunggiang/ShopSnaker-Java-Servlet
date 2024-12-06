<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hồ sơ cá nhân</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header text-center bg-secondary text-white">
                <h2>Hồ sơ cá nhân</h2>
            </div>
            <div class="card-body text-center">
                <img src="${userProfile.profileImagePath}" alt="Ảnh đại diện" class="img-thumbnail" width="150">
                <h3 class="mt-3 text-gray">${sessionScope.username}</h3>
                <p><strong>Email:</strong> ${userProfile.email}</p>
                <p><strong>Số điện thoại:</strong> ${userProfile.phoneNumber}</p>
                <p><strong>Địa chỉ:</strong> ${userProfile.address}</p>
                <p><strong>Quê quán:</strong> ${userProfile.from}</p>
            </div>
          
            <div class="card-footer text-center">
                <a href="${pageContext.request.contextPath}/edit-profile" class="btn btn-primary">Cập nhật thông tin</a>

            </div>
        </div>
    </div>
</body>
</html>
