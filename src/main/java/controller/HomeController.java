package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;

import java.io.IOException;

import java.io.IOException;
import java.util.List;

import dao.*;

@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

    private CategoryDAO categoryDAO = new CategoryDAO();
    private ProductDAO productDAO = new ProductDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryDAO.getAllCategories();
        List<Product> products = productDAO.getAllProducts();

        request.setAttribute("categories", categories);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/home/home.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Giả lập dữ liệu mẫu
//        List<Category> categories = CategoryDAO.getAllCategories();
//        List<Product> products = ProductDAO.getAllProducts();
//
//        request.setAttribute("categories", categories);
//        request.setAttribute("products", products);
//        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
//    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String[] categoryIds = request.getParameter("categoryIds").split(",");
//        List<Product> filteredProducts = productDAO.getProductsByCategories(categoryIds);
//
//        request.setAttribute("products", filteredProducts);
//        request.getRequestDispatcher("/WEB-INF/views/partials/productList.jsp").forward(request, response);
//    }

}
