package com.yash.cart.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Cart") // Tells Spring Data to store this object in Redis as a hash
public class Cart {

	@Id
	private String email; // The user's ID who owns the cart
	private List<CartItem> items;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
}
