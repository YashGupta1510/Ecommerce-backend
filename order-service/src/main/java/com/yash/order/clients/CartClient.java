package com.yash.order.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yash.order.dtos.CartDTO;

@FeignClient(name = "cart-service")
public interface CartClient {

	@GetMapping("/api/cart/{email}")
	CartDTO getCart(@PathVariable("email") String email);

	@DeleteMapping("/api/cart/{email}/clear")
	void clearCart(@PathVariable("email") String email);
}

