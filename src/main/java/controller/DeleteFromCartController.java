package controller;
import java.io.IOException;
import java.util.List;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.*;

@WebServlet("/delete-from-cart")
public class DeleteFromCartController extends HttpServlet {

    private CartDAO cartDAO = new CartDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
            
            boolean isDeleted = cartDAO.deleteCartItem(cartItemId);
            System.out.println("Cart item id: "+cartItemId);

            if (isDeleted) {
                response.sendRedirect(request.getContextPath() + "/cart");
                System.out.println("Cart item id: "+cartItemId);
            } else {
                request.setAttribute("error", "Không thể xóa sản phẩm khỏi giỏ hàng.");
                request.getRequestDispatcher("/home/cart.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Lỗi: ID sản phẩm không hợp lệ.");
            request.getRequestDispatcher("/home/cart.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Đã xảy ra lỗi không xác định: " + e.getMessage());
            request.getRequestDispatcher("/home/cart.jsp").forward(request, response);
        }
    }
}
