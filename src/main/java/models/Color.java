package models;

public class Color {
	private int colorId;
	private int productId;
	private String colorValue;
	public Color(int colorId, int productId, String colorValue) {
		super();
		this.colorId = colorId;
		this.productId = productId;
		this.colorValue = colorValue;
	}
	public Color() {}
	public int getColorId() {
		return colorId;
	}
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getColorValue() {
		return colorValue;
	}
	public void setColorValue(String colorValue) {
		this.colorValue = colorValue;
	}
	
	
	
}
