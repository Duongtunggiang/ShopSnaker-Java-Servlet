package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import dao.AccountDao;
import dao.CustomerWalletDAO;
import dao.SalerWalletDAO;
import models.Account;
import models.Customer; // Thêm import cho Customer
import models.Saler; // Thêm import cho Saler
import models.Admin; // Thêm import cho Admin

@WebServlet(urlPatterns = {"/register", "/login", "/logout"})
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
//            case "/profile":
//                handleProfile(request, response);
//                break;
            default:
                response.getWriter().append("Served at: ").append(request.getContextPath());
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/register".equals(action)) {
            try {
				handleRegister(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if ("/login".equals(action)) {
            handleLogin(request, response);
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        int roleID = Integer.parseInt(request.getParameter("roleID")); // Lấy RoleID từ form

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu không khớp");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (accountDAO.getAccountByUsername(username) != null) {
            request.setAttribute("error", "Tên đăng nhập đã tồn tại");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Tạo tài khoản mới
        Account newAccount = new Account(0, username, password, roleID);
        accountDAO.saveUser(newAccount); 

        // Lấy AccountID vừa tạo
        int accountID = accountDAO.getAccountId(username);

        if (roleID == 1) { 
            Admin admin = new Admin();
            admin.setAccountID(accountID);
            accountDAO.saveAdmin(admin);

        } else if (roleID == 3) { 
            Saler saler = new Saler();
            saler.setAccountID(accountID); // Liên kết với AccountID
            accountDAO.saveSaler(saler);

            // Lấy SalerID vừa tạo
            int salerID = accountDAO.getSalerId(accountID);

            // Tạo ví cho người bán
            SalerWalletDAO salerWalletDAO = new SalerWalletDAO();
            salerWalletDAO.createSalerWallet(salerID);

        } else if (roleID == 2) { 
            Customer customer = new Customer();
            customer.setAccountID(accountID); // Liên kết với AccountID
            accountDAO.saveCustomer(customer);

            // Lấy CustomerID vừa tạo
            int customerID = accountDAO.getCustomerId(accountID);

            // Tạo ví cho khách hàng
            CustomerWalletDAO customerWalletDAO = new CustomerWalletDAO();
            customerWalletDAO.createCustomerWallet(customerID);
        }

        response.sendRedirect("login.jsp");
    }


    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra đăng nhập và lấy vai trò người dùng
        String userRole = accountDAO.checkLogin(username, password);

        if (userRole != null) {
            HttpSession session = request.getSession(); // Tạo hoặc lấy session
            session.setAttribute("username", username); // Lưu tên người dùng vào session
            session.setAttribute("role", userRole); // Lưu vai trò vào session

            switch (userRole) {
                case "Admin":
                    response.sendRedirect("admin");
                    break;
                case "Saler":
                    response.sendRedirect("saler");
                    break;
                case "Customer":
                    response.sendRedirect("home");
                    break;
                default:
                    response.sendRedirect("login.jsp");
                    break;
            }
        } else {
            String errorMessage = "Tên đăng nhập hoặc mật khẩu không đúng";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Lấy session hiện tại
        if (session != null) {
            session.invalidate(); 
        }
        response.sendRedirect("login.jsp");
    }

//    private void handleProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("username") == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        String username = (String) session.getAttribute("username");
//        Account account = accountDAO.getAccountByUsername(username);
//
//        if (account != null) {
//            request.setAttribute("account", account);
//            request.getRequestDispatcher("profile.jsp").forward(request, response);
//        } else {
//            response.sendRedirect("login.jsp");
//        }
//    }
}
