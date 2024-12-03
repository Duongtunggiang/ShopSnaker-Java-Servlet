package models;

import java.math.BigDecimal;

public class BookingDetails {
	private int bookingDetailId;
	private int bookingId;
	private int productId;
	private int quanlity;
	private BigDecimal price;
	public BookingDetails(int bookingDetailId, int bookingId, int productId, int quanlity, BigDecimal price) {
		super();
		this.bookingDetailId = bookingDetailId;
		this.bookingId = bookingId;
		this.productId = productId;
		this.quanlity = quanlity;
		this.price = price;
	}
	public int getBookingDetailId() {
		return bookingDetailId;
	}
	public void setBookingDetailId(int bookingDetailId) {
		this.bookingDetailId = bookingDetailId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuanlity() {
		return quanlity;
	}
	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
