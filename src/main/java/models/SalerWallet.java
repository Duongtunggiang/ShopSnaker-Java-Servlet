package models;

import java.math.BigDecimal;

public class SalerWallet {
	private int walletId;
	private int salerId;
	private BigDecimal balance;
	public SalerWallet(int walletId, int salerId, BigDecimal balance) {
		super();
		this.walletId = walletId;
		this.salerId = salerId;
		this.balance = balance;
	}
	public int getWalletId() {
		return walletId;
	}
	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}
	public int getSalerId() {
		return salerId;
	}
	public void setSalerId(int salerId) {
		this.salerId = salerId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
}
