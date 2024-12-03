package models;

import java.math.BigDecimal;

public class CustomerWallet {
	private int walletId;
	private int customerId;
	private BigDecimal balance;
	public CustomerWallet(int walletId, int customerId, BigDecimal balance) {
		super();
		this.walletId = walletId;
		this.customerId = customerId;
		this.balance = balance;
	}
	public int getWalletId() {
		return walletId;
	}
	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
}
