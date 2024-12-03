package models;

public class ProducImages {
	private int imageId;
	private int productId;
	private String imagePath;
	public ProducImages(int imageId, int productId, String imagePath) {
		super();
		this.imageId = imageId;
		this.productId = productId;
		this.imagePath = imagePath;
	}
	public ProducImages() {
		// TODO Auto-generated constructor stub
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
