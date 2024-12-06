<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa hồ sơ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header text-center bg-secondary text-white">
                <h2>Chỉnh sửa thông tin</h2>
            </div>
            <div class="card-body">
                <form action="update-profile" method="post">
                    <div class="mb-3">
                        <label for="firstName" class="form-label">Họ</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" value="${userProfile.firstName}">
                    </div>
                    <div class="mb-3">
                        <label for="lastName" class="form-label">Tên</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" value="${userProfile.lastName}">
                    </div>
                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Số điện thoại</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${userProfile.phoneNumber}">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Địa chỉ</label>
                        <input type="text" class="form-control" id="address" name="address" value="${userProfile.address}">
                    </div>
                    <div class="mb-3">
                        <label for="from" class="form-label">Quê quán</label>
                        <input type="text" class="form-control" id="from" name="from" value="${userProfile.from}">
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-success">Lưu thay đổi</button>
                        <a href="profile" class="btn btn-secondary">Hủy</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
