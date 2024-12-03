package models;

public class Size {
	private int sizeId;
	private int productId;
	private String sizeValue;
	public Size(int sizeId, int productId, String sizeValue) {
		super();
		this.sizeId = sizeId;
		this.productId = productId;
		this.sizeValue = sizeValue;
	}
	public Size() {
		// TODO Auto-generated constructor stub
	}
	public int getSizeId() {
		return sizeId;
	}
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getSizeValue() {
		return sizeValue;
	}
	public void setSizeValue(String sizeValue) {
		this.sizeValue = sizeValue;
	}
	
	
}
