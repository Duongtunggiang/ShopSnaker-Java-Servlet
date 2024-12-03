package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Booking {
	private int bookingId;
	private int customerId;
	private String bookingCode;
	private String Status;
	private BigDecimal totalAmount;
	private LocalDateTime createDate;
	public Booking(int bookingId, int customerId, String bookingCode, String status, BigDecimal totalAmount,
			LocalDateTime createDate) {
		super();
		this.bookingId = bookingId;
		this.customerId = customerId;
		this.bookingCode = bookingCode;
		Status = status;
		this.totalAmount = totalAmount;
		this.createDate = createDate;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getBookingCode() {
		return bookingCode;
	}
	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	
}
