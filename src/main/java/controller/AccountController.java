package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import dao.AccountDao;
import models.Account;

@WebServlet(urlPatterns = {"/register", "/login", "/logout", "/profile"})
public class AccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountDao accountDAO;

    public AccountController() {
        super();
        accountDAO = new AccountDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/register":
                request.getRequestDispatcher("register.jsp").forward(request, response);
                break;
            case "/login":
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            case "/logout":
                handleLogout(request, response);
                break;
            case "/profile":
                handleProfile(request, response);
                break;
            default:
                response.getWriter().append("Served at: ").append(request.getContextPath());
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/register".equals(action)) {
            handleRegister(request, response);
        } else if ("/login".equals(action)) {
            handleLogin(request, response);
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        int roleID = Integer.parseInt(request.getParameter("roleID"));

        // Kiểm tra mật khẩu khớp
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu không khớp");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Kiểm tra username đã tồn tại
        if (accountDAO.getAccountByUsername(username) != null) {
            request.setAttribute("error", "Tên đăng nhập đã tồn tại");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Thực hiện đăng ký, lưu vào cơ sở dữ liệu
        Account newAccount = new Account(0, username, password, roleID);
        accountDAO.saveUser(newAccount);

        response.sendRedirect("login.jsp");
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra thông tin đăng nhập và vai trò
        String userRole = accountDAO.checkLogin(username, password);

        if (userRole != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", userRole);
            switch (userRole) {
                case "Admin":
                    response.sendRedirect("admin.jsp");
                    break;
                case "Saler":
                    response.sendRedirect("saler.jsp");
                    break;
                case "Customer":
                    response.sendRedirect("home.jsp");
                    break;
                default:
                    response.sendRedirect("login.jsp");
                    break;
            }
        } else {
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // Xóa phiên làm việc
        }
        response.sendRedirect("login.jsp");
    }

    private void handleProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        Account account = accountDAO.getAccountByUsername(username);

        if (account != null) {
            request.setAttribute("account", account);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
