package com.yash.cart.entity;

public class CartItem {

	private String skuCode;
	private double price;
	private int quantity;

	public CartItem(String productId, int quantity, double price) {
		this.skuCode = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
