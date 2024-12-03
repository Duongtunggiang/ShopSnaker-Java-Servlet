package controller;

import java.io.IOException;
import java.util.List;

import dao.ColorDAO;
import dao.GiftDAO;
import dao.ProductDAO;
import dao.ProductDetailsDAO;
import dao.ProductImagesDAO;
import dao.RelatedArticlesDAO;
import dao.SizeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Color;
import models.Gift;
import models.ProducImages;
import models.Product;
import models.ProductDetails;
import models.RelatedArticles;
import models.Size;

@WebServlet("/product-detail")
public class ProductDetailServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
    
    private ProductDAO productDAO = new ProductDAO();
    private SizeDAO sizeDAO = new SizeDAO();
    private ColorDAO colorDAO = new ColorDAO();
    private ProductImagesDAO productImagesDAO = new ProductImagesDAO();
    private ProductDetailsDAO productDetailsDAO = new ProductDetailsDAO();
    private GiftDAO giftDAO = new GiftDAO();
    private RelatedArticlesDAO relatedArticlesDAO = new RelatedArticlesDAO();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Lấy thông tin sản phẩm
        Product product = productDAO.getProductById(productId);
        if (product != null) {
            // Lấy các thông tin chi tiết khác
            List<Size> sizes = sizeDAO.getSizesByProductId(productId);
            List<Color> colors = colorDAO.getColorsByProductId(productId);
            List<ProducImages> images = productImagesDAO.getProductImagesByProductId(productId);
            ProductDetails productDetails = productDetailsDAO.getProductDetailsByProductId(productId);
            List<Gift> gifts = giftDAO.getGiftsByProductId(productId);
            List<RelatedArticles> relatedArticles = relatedArticlesDAO.getRelatedArticlesByProductId(productId);

            
            request.setAttribute("product", product);
            request.setAttribute("sizes", sizes);
            request.setAttribute("colors", colors);
            request.setAttribute("images", images);
            request.setAttribute("productDetails", productDetails);
            request.setAttribute("gifts", gifts);
            request.setAttribute("relatedArticles", relatedArticles);

            // Forward đến trang JSP chi tiết sản phẩm
            request.getRequestDispatcher("/home/product-detail.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không có sản phẩm.");
        }
    }
}
