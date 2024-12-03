package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.Gift;
import models.RelatedArticles;
public class RelatedArticlesDAO {
	private DBContext dbContext = new DBContext();

    // Lấy tất cả bài viết liên quan
    public List<RelatedArticles> getAllRelatedArticles() {
        List<RelatedArticles> articles = new ArrayList<>();
        String sql = "SELECT * FROM RelatedArticles";
        try (Connection conn = dbContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RelatedArticles article = new RelatedArticles();
                article.setArticlesId(rs.getInt("ArticleID"));
                article.setProductId(rs.getInt("ProductID"));
                article.setTitle(rs.getString("Title"));
                article.setContent(rs.getString("Content"));
                article.setCreateDate(rs.getTimestamp("CreatedDate").toLocalDateTime());
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    // Lấy bài viết liên quan theo ProductID
    public List<RelatedArticles> getRelatedArticlesByProductId(int productId) {
        List<RelatedArticles> articles = new ArrayList<>();
        String sql = "SELECT * FROM RelatedArticles WHERE ProductID = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RelatedArticles article = new RelatedArticles();
                    article.setArticlesId(rs.getInt("ArticleID"));
                    article.setProductId(rs.getInt("ProductID"));
                    article.setTitle(rs.getString("Title"));
                    article.setContent(rs.getString("Content"));
                    article.setCreateDate(rs.getTimestamp("CreatedDate").toLocalDateTime());
                    articles.add(article);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }
}
