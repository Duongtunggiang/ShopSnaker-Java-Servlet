<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Đăng Ký</h2>
        <form action="register" method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Xác nhận mật khẩu</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
            </div>
            <div class="form-group">
                <label for="roleID">Vai trò</label>
                <select class="form-control" id="roleID" name="roleID" required>
                    <option value="1">Admin</option>
                    <option value="2">Khách hàng</option>
                    <option value="3">Bán hàng</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Đăng Ký</button>
            <div class="mt-3">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>
            </div>
            <p class="mt-3">Đã có tài khoản? <a href="login">Đăng nhập tại đây</a></p>
        </form>
    </div>
</body>
</html>
