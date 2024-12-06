package controller;

import java.io.IOException;
import java.util.List;

import dao.AccountDao;
import dao.CartDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.CartItem;

@WebServlet("/cart")
public class CartController extends HttpServlet {

    private CartDAO cartDAO = new CartDAO();
    private AccountDao accountDao = new AccountDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username"); // Lấy username từ session

        if (username != null) {
            // Lấy CustomerID dựa vào username
            Integer customerId = accountDao.getCustomerIdByUsername(username);

            if (customerId != null) {
                // Lấy danh sách CartItems dựa vào CustomerID
                List<CartItem> cartItems = cartDAO.getCartItemsByCustomerId(customerId);
                request.setAttribute("cartItems", cartItems);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/home/cart.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("login.jsp"); // Nếu không tìm thấy CustomerID
            }
        } else {
            response.sendRedirect("login.jsp"); // Nếu không có username trong session
        }
        
    }
}

