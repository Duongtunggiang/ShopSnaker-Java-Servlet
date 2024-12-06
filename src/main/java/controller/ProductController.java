package controller;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import dao.AccountDao;
import dao.BookingDAO;
import dao.CategoryDAO;
import models.Product;
import models.Booking;
import models.Category;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/saler")
@MultipartConfig
public class ProductController extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "salerHome";  
        }

        switch (action) {
            case "salerHome":
                request.getRequestDispatcher("saler/salerHome.jsp").forward(request, response); 
                break;
                
            case "myProducts":
                List<Product> products = productDAO.getAllProducts();
                request.setAttribute("products", products);
                request.getRequestDispatcher("saler/productList.jsp").forward(request, response); 
                break;

            case "createProduct":
                List<Category> categories = categoryDAO.getAllCategories();
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("saler/createProduct.jsp").forward(request, response);
                break;

            case "createCategory":
                request.getRequestDispatcher("saler/createCategory.jsp").forward(request, response);
                break;

            case "editProduct":
                int productId = Integer.parseInt(request.getParameter("id"));
                Product product = productDAO.getProductById(productId);
                request.setAttribute("product", product);
                request.getRequestDispatcher("saler/editProduct.jsp").forward(request, response); 
                break;
            case "updateStatus":
                // Lấy thông tin từ form
                int productIdS = Integer.parseInt(request.getParameter("productId"));
                String status = request.getParameter("status");

                // Tạo BookingDAO để gọi phương thức cập nhật trạng thái
                BookingDAO bookingDAO = new BookingDAO();
                
                // Cập nhật trạng thái trong bảng booking
                bookingDAO.updateBookingStatus(productIdS, status);

                // Sau khi cập nhật xong, bạn có thể gửi lại thông báo hoặc chuyển hướng về trang sản phẩm
                response.sendRedirect("saler?action=myProducts"); // Hoặc bạn có thể quay lại trang chi tiết sản phẩm
                break;

 

            default:
                response.sendRedirect("saler?action=salerHome");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("error", "Hành động không hợp lệ.");
            request.getRequestDispatcher("saler/createProduct.jsp").forward(request, response);
            return; 
        }

        switch (action) {


        case "saveProduct":
            try {
                HttpSession session = request.getSession();
                String username = (String) session.getAttribute("username");

                AccountDao accountDao = new AccountDao();
                Integer salerID = accountDao.getSalerIDByUsername(username);

                if (salerID == null) {
                    request.setAttribute("error", "Không tìm thấy Saler ID.");
                    request.getRequestDispatcher("saler/createProduct.jsp").forward(request, response);
                    return;
                }

             // Lấy Category Name từ form và tìm ID của danh mục đó
                String categoryIDStr = request.getParameter("categoryName"); // Lấy giá trị categoryName từ form
                if (categoryIDStr == null || categoryIDStr.trim().isEmpty()) {
                    request.setAttribute("error1", "Danh mục không hợp lệ.");
                    request.getRequestDispatcher("saler/createProduct.jsp").forward(request, response);
                    return;
                }

                String categoryID = categoryDAO.getCateIDbyCateName(categoryIDStr); // Lấy Category ID từ tên danh mục
                if (categoryID == null) {
                    request.setAttribute("error1", "Không tìm thấy danh mục với tên: " + categoryIDStr);
                    request.getRequestDispatcher("saler/createProduct.jsp").forward(request, response);
                    return;
                }
                int cateID = Integer.parseInt(categoryID);
                if (cateID <= 0) {
                    request.setAttribute("error1", "Danh mục không hợp lệ. Giá trị nhận được: " + categoryIDStr);
                    request.getRequestDispatcher("saler/createProduct.jsp").forward(request, response);
                    return;
                }


                // Lấy các tham số khác từ form
                String productName = request.getParameter("productName");

                String priceStr = request.getParameter("price");
                BigDecimal price;
                try {
                    price = new BigDecimal(priceStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("error3", "Giá không hợp lệ.");
                    request.getRequestDispatcher("saler/createProduct.jsp").forward(request, response);
                    return;
                }

                String discountStr = request.getParameter("discount");
                BigDecimal discount = BigDecimal.ZERO;
                try {
                    if (discountStr != null && !discountStr.isEmpty()) {
                        discount = new BigDecimal(discountStr);
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error3", "Giảm giá không hợp lệ.");
                    request.getRequestDispatcher("saler/createProduct.jsp").forward(request, response);
                    return;
                }

                // Xử lý upload ảnh
                String imagePath = null;
                Part filePart = request.getPart("productImagePath"); // Đảm bảo tên input là productImagePath trong form
                if (filePart != null && filePart.getSize() > 0) {
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String uploadDir = getServletContext().getRealPath("/images/products");
                    File uploadDirFile = new File(uploadDir);

                    if (!uploadDirFile.exists()) {
                        uploadDirFile.mkdirs();
                    }

                    // Đường dẫn lưu ảnh
                    imagePath = "images/products/" + fileName;
                    filePart.write(uploadDir + File.separator + fileName);
                }

                // Lấy các tham số khác
                String color = request.getParameter("color");
                String style = request.getParameter("style");

                int quality = 1;
                String qualityStr = request.getParameter("quality");
                try {
                    if (qualityStr != null && !qualityStr.isEmpty()) {
                        quality = Integer.parseInt(qualityStr);
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error3", "Số lượng không hợp lệ.");
                    request.getRequestDispatcher("saler/createProduct.jsp").forward(request, response);
                    return;
                }

                Product newProduct = new Product();
                newProduct.setSalerID(salerID);
                newProduct.setCategoryID(cateID);
                newProduct.setProductName(productName);
                newProduct.setQuality(quality);
                newProduct.setPrice(price);
                newProduct.setDiscount(discount);
                newProduct.setProductImagePath(imagePath);
                newProduct.setColor(color);
                newProduct.setStyle(style);
                newProduct.setStatus("Chưa thể bán");

                int productId = productDAO.addProduct(newProduct);
                if (productId > 0) {
                    response.sendRedirect("saler?action=myProducts");
                } else {
                    request.setAttribute("error6", "Không thể tạo sản phẩm.");
                    request.getRequestDispatcher("saler/createProduct.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("error6", "Đã xảy ra lỗi: " + e.getMessage());
                request.getRequestDispatcher("saler/createProduct.jsp").forward(request, response);
            }
            break;


    
        
        case "saveCategory": // Xử lý lưu danh mục
            Category newCategory = new Category();
            newCategory.setCategoryName(request.getParameter("name"));
            newCategory.setBrandImagePath(request.getParameter("brandImagePath")); // Lưu đường dẫn hình ảnh

            if (categoryDAO.addCategory(newCategory)) {
                response.sendRedirect("saler?action=createProduct"); // Redirect về trang tạo sản phẩm
            } else {
                request.setAttribute("error", "Không thể thêm danh mục.");
                request.getRequestDispatcher("saler/createCategory.jsp").forward(request, response); // Cập nhật đường dẫn
            }
            break;
            
            
        case "updateProduct":
            Product updatedProduct = new Product();
            updatedProduct.setProductID(Integer.parseInt(request.getParameter("id")));
            updatedProduct.setSalerID(Integer.parseInt(request.getParameter("salerID")));
            updatedProduct.setCategoryID(Integer.parseInt(request.getParameter("categoryID")));
            updatedProduct.setProductName(request.getParameter("name"));
            updatedProduct.setQuality(Integer.parseInt(request.getParameter("quality")));
            updatedProduct.setPrice(new BigDecimal(request.getParameter("price")));
            updatedProduct.setDiscount(new BigDecimal(request.getParameter("discount")));
            updatedProduct.setProductImagePath(request.getParameter("imagePath"));
            updatedProduct.setColor(request.getParameter("color"));
            updatedProduct.setStyle(request.getParameter("style"));

            if (productDAO.updateProduct(updatedProduct)) { 
                response.sendRedirect("saler?action=myProducts");
            } else {
                request.setAttribute("error", "Không thể cập nhật sản phẩm.");
                request.getRequestDispatcher("saler/editProduct.jsp").forward(request, response); // Cập nhật đường dẫn
            }
            break;

            
        case "updateStatus":
            try {
                // Lấy thông tin từ request
                int productId = Integer.parseInt(request.getParameter("productId"));
                String newStatus = request.getParameter("status");
                System.out.println("Product ID: " + productId);
                System.out.println("New Status: " + newStatus);

                // Cập nhật trạng thái sản phẩm
                ProductDAO productDAO = new ProductDAO();
                boolean isUpdated = productDAO.updateProductStatus(productId, newStatus);

                if (isUpdated) {
                    // Sau khi cập nhật thành công, gửi thông báo và chuyển hướng
                    request.setAttribute("successMessage", "Trạng thái sản phẩm đã được cập nhật thành công!");
                } else {
                    // Thông báo lỗi nếu không cập nhật được
                    request.setAttribute("errorMessage", "Không thể cập nhật trạng thái sản phẩm.");
                }
                response.sendRedirect("saler?action=myProducts");

            } catch (Exception e) {
                // Xử lý lỗi nếu có
                request.setAttribute("errorMessage", "Đã xảy ra lỗi: " + e.getMessage());
                request.getRequestDispatcher("saler/productList.jsp").forward(request, response);
            }
            break;

        case "deleteProduct":
            try {
                int productId = Integer.parseInt(request.getParameter("id"));
                
                boolean isDeleted = productDAO.deleteProduct(productId);
                	System.out.println("ID delete: " + productId);
                if (isDeleted) {
                    response.sendRedirect("saler?action=myProducts");
                    System.out.println("Delete Success!");
                } else {
                    request.setAttribute("error", "Không thể xóa sản phẩm. Có thể sản phẩm không tồn tại hoặc có lỗi hệ thống.");
                    request.getRequestDispatcher("saler/productList.jsp").forward(request, response);
                   
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Lỗi: ID sản phẩm không hợp lệ.");
                request.getRequestDispatcher("saler/productList.jsp").forward(request, response);
            
            } catch (Exception e) {
                request.setAttribute("error", "Đã xảy ra lỗi không xác định: " + e.getMessage());
                request.getRequestDispatcher("saler/productList.jsp").forward(request, response);
            }
            
            break;
            


            
        default:
            response.sendRedirect("saler?action=myProducts");
            break;

        }
    }
}
