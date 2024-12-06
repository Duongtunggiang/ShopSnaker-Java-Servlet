package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transactions {
	private int transactionsId;
	private int bookingId;
	private BigDecimal amount;
	private LocalDateTime transactionsDate;
	private String status;
	private String paymentMethod;
	private int customerWalletId;
	private int salerWalletId;
	public Transactions(int transactionsId, int bookingId, BigDecimal amount, LocalDateTime transactionsDate,
			String status, String paymentMethod, int customerWalletId, int salerWalletId) {
		super();
		this.transactionsId = transactionsId;
		this.bookingId = bookingId;
		this.amount = amount;
		this.transactionsDate = transactionsDate;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.customerWalletId = customerWalletId;
		this.salerWalletId = salerWalletId;
	}
	public Transactions() {
		super();
	}
	public int getTransactionsId() {
		return transactionsId;
	}
	public void setTransactionsId(int transactionsId) {
		this.transactionsId = transactionsId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public LocalDateTime getTransactionsDate() {
		return transactionsDate;
	}
	public void setTransactionsDate(LocalDateTime transactionsDate) {
		this.transactionsDate = transactionsDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public int getCustomerWalletId() {
		return customerWalletId;
	}
	public void setCustomerWalletId(int customerWalletId) {
		this.customerWalletId = customerWalletId;
	}
	public int getSalerWalletId() {
		return salerWalletId;
	}
	public void setSalerWalletId(int salerWalletId) {
		this.salerWalletId = salerWalletId;
	}
	
	
	
	
}
