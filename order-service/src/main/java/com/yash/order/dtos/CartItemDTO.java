package com.yash.order.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CartItemDTO {
	@Id
	private String skuCode;
	private int quantity;
	private double price;

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}