package models;

public class Gift {
	private int giftId;
	private int productId;
	private String giftName;
	private String description;
	private int quantity;
	public Gift(int giftId, int productId, String giftName, String description, int quantity) {
		super();
		this.giftId = giftId;
		this.productId = productId;
		this.giftName = giftName;
		this.description = description;
		this.quantity = quantity;
	}
	public Gift() {
		// TODO Auto-generated constructor stub
	}
	public int getGiftId() {
		return giftId;
	}
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
