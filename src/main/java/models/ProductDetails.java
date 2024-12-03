package models;

public class ProductDetails {
	private int detailId;
	private int productId;
	private String description;
	public ProductDetails(int detailId, int productId, String description) {
		super();
		this.detailId = detailId;
		this.productId = productId;
		this.description = description;
	}
	public ProductDetails() {
		// TODO Auto-generated constructor stub
	}
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	} 
	
	
}
