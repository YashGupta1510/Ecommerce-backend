package com.yash.order.dtos;

import java.util.List;

//DTO for Cart data (coming from Cart Service)
public class CartDTO {
	private String email;
	private List<CartItemDTO> items;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<CartItemDTO> getItems() {
		return items;
	}

	public void setItems(List<CartItemDTO> items) {
		this.items = items;
	}

}
