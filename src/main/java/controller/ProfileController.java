package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AccountDao;
import dao.CustomerDAO;
import dao.SalerDao;
import models.Account;
import models.Customer;
import models.Saler;

@WebServlet(name = "ProfileController", urlPatterns = {"/profile", "/edit-profile", "/update-profile"})
public class ProfileController extends HttpServlet {

    private final AccountDao accountDao = new AccountDao();
    private final CustomerDAO customerDao = new CustomerDAO();
    private final SalerDao salerDao = new SalerDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy Account từ username
        Account account = accountDao.getAccountByUsername(username);
        
        String action = request.getServletPath();
        if (action.equals("/profile")) {
            // Hiển thị trang hồ sơ
            if (account.getRoleID() == 2) { // RoleID 2 cho Customer
                Customer customer = customerDao.getCustomerByAccountID(account.getAccountID());
                request.setAttribute("userProfile", customer);
            } else if (account.getRoleID() == 3) { // RoleID 3 cho Saler
                Saler saler = salerDao.getSalerByAccountID(account.getAccountID());
                request.setAttribute("userProfile", saler);
            }
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else if (action.equals("/edit-profile")) {
            // Chuyển đến trang chỉnh sửa
            if (account.getRoleID() == 2) {
                Customer customer = customerDao.getCustomerByAccountID(account.getAccountID());
                request.setAttribute("userProfile", customer);
            } else if (account.getRoleID() == 3) {
                Saler saler = salerDao.getSalerByAccountID(account.getAccountID());
                request.setAttribute("userProfile", saler);
            }
            request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy Account từ username
        Account account = accountDao.getAccountByUsername(username);

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String from = request.getParameter("from");

        if (account.getRoleID() == 2) { // Cập nhật Customer
            Customer customer = customerDao.getCustomerByAccountID(account.getAccountID());
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setPhoneNumber(phoneNumber);
            customer.setAddress(address);
            customer.setFrom(from);
            customerDao.updateCustomer(customer);
        } else if (account.getRoleID() == 3) { // Cập nhật Saler
            Saler saler = salerDao.getSalerByAccountID(account.getAccountID());
            saler.setFirstName(firstName);
            saler.setLastName(lastName);
            saler.setPhoneNumber(phoneNumber);
            saler.setAddress(address);
            saler.setFrom(from);
            salerDao.updateSaler(saler);
        }

        response.sendRedirect("profile");
    }
}
