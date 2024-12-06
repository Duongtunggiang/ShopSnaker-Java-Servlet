package models;

import java.math.BigDecimal;

public class Product {
    private int productID;
    private int salerID;
    private int categoryID;
    private String productName;
    private int quality;
    private BigDecimal price;
    private BigDecimal discount;
    private String productImagePath;
    private String color;
    private String style;
    private String status;
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Thêm đối tượng Booking để lưu trữ status từ bảng Booking
    private Booking booking;

    // Getter và Setter cho Booking
    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    // Constructor
    public Product() {
    }

	public Product(int productID, int salerID, int categoryID, String productName, int quality, BigDecimal price,
			BigDecimal discount, String productImagePath, String color, String style, String status, Booking booking) {
		super();
		this.productID = productID;
		this.salerID = salerID;
		this.categoryID = categoryID;
		this.productName = productName;
		this.quality = quality;
		this.price = price;
		this.discount = discount;
		this.productImagePath = productImagePath;
		this.color = color;
		this.style = style;
		this.status = status;
		this.booking = booking;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getSalerID() {
		return salerID;
	}

	public void setSalerID(int salerID) {
		this.salerID = salerID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
    
   
}
