package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;

import java.io.IOException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Category> categories = categoryDAO.getAllCategories();
//        String categoryIdsParam = request.getParameter("id");
//        List<Product> products;
//
//        if (categoryIdsParam == null || categoryIdsParam.isEmpty()) {
//            products = productDAO.getAllProducts();
//        } else {
//            
//            products = productDAO.getProductsByCategoryId(categoryIdsParam);
//        }
//
//        request.setAttribute("categories", categories);
//        request.setAttribute("products", products);
//        request.getRequestDispatcher("/home/home.jsp").forward(request, response);
//    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryDAO.getAllCategories();
        String[] categoryIdsParam = request.getParameterValues("id");
        List<Product> products;

        if (categoryIdsParam == null || categoryIdsParam.length == 0) {
            products = productDAO.getAvailableProducts(); // Lấy sản phẩm có thể bán
        } else {
            List<Integer> categoryIds = Arrays.stream(categoryIdsParam)
                                              .map(Integer::parseInt)
                                              .collect(Collectors.toList());
            products = productDAO.getProductsByCategories(categoryIds); // Lọc theo danh mục và trạng thái
        }

        request.setAttribute("categories", categories);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/home/home.jsp").forward(request, response);
    }





    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
