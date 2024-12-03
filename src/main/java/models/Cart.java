package models;

public class Cart {
	private int cartId;
	private int customerId;
	public Cart(int cartId, int customerId) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
