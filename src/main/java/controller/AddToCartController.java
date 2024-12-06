package controller;

import java.io.IOException;
import java.math.BigDecimal;

import dao.AccountDao;
import dao.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Cart;
import models.CartItem;

@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {

    private CartDAO cartDAO = new CartDAO();
    private AccountDao accountDAO = new AccountDao(); // Giả sử bạn có một AccountDAO để lấy customerId từ username

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Kiểm tra xem người dùng đã đăng nhập chưa
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy CustomerID từ username
        Integer customerId = accountDAO.getCustomerIdByUsername(username);
        if (customerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy hoặc tạo CartID cho Customer
        int cartId = cartDAO.getCartIdByCustomerId(customerId);
        if (cartId == -1) {
            cartId = cartDAO.createCartForCustomer(customerId);
        }

        // Lấy thông tin sản phẩm từ request
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        

        // Tạo CartItem và thêm vào cơ sở dữ liệu
        CartItem cartItem = new CartItem();
        cartItem.setCartId(cartId);
        cartItem.setProductId(productId);
        cartItem.setProductName(productName);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(price);

        boolean isAdded = cartDAO.addCartItem(cartItem);
        if (isAdded) {
        	response.sendRedirect(request.getContextPath() + "/cart");// Chuyển hướng về trang giỏ hàng
        } else {
            response.getWriter().println("Lỗi khi thêm sản phẩm vào giỏ hàng");
        }
//        System.out.println("CartID: " + cartItem.getCartId());
//        System.out.println("ProductID: " + cartItem.getProductId());
//        System.out.println("Quantity: " + cartItem.getQuantity());
//        System.out.println("Price: " + cartItem.getPrice());
//        System.out.println("ProductName: " + cartItem.getProductName());

    }
}

