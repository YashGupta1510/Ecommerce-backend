package com.yash.cart.controller;

import com.yash.cart.entity.Cart;
import com.yash.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("/{email}")
	public Cart getCart(@PathVariable String email) {
		return cartService.getCart(email);
	}

	@PostMapping("/{email}/add")
	public Cart addToCart(@PathVariable String email, @RequestParam String skuCode, @RequestParam int quantity, @RequestParam double price) {
		return cartService.addToCart(email, skuCode, quantity, price);
	}

	@DeleteMapping("/{email}/remove")
	public Cart removeFromCart(@PathVariable String email, @RequestParam String skuCode) {
		return cartService.removeFromCart(email, skuCode);
	}

	@DeleteMapping("/{email}/clear")
	public void clearCart(@PathVariable String email) {
		cartService.clearCart(email);
	}
}
