package models;

import java.time.LocalDateTime;

public class RelatedArticles {
	private int articlesId;
	private int productId;
	private String title;
	private String content;
	private LocalDateTime createDate;
	public RelatedArticles(int articlesId, int productId, String title, String content, LocalDateTime createDate) {
		super();
		this.articlesId = articlesId;
		this.productId = productId;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
	}
	public RelatedArticles() {
		// TODO Auto-generated constructor stub
	}
	public int getArticlesId() {
		return articlesId;
	}
	public void setArticlesId(int articlesId) {
		this.articlesId = articlesId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	
}
