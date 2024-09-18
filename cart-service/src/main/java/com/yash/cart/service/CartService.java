package com.yash.cart.service;

import com.yash.cart.entity.Cart;
import com.yash.cart.entity.CartItem;
import com.yash.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

 @Autowired
 private CartRepository cartRepository;

 // Retrieve the cart for a specific user
 public Cart getCart(String email) {
     return cartRepository.findById(email).orElse(new Cart());
 }

 // Add a product to the cart
 public Cart addToCart(String email, String skuCode, int quantity, double price) {
     Cart cart = getCart(email); // Retrieve existing cart or create new one
     boolean itemExists = false;

     // Check if product is already in cart
     for (CartItem item : cart.getItems()) {
         if (item.getSkuCode().equals(skuCode)) {
             item.setQuantity(item.getQuantity() + quantity); // Update quantity
             itemExists = true;
             break;
         }
     }

     if (!itemExists) {
         // Add new item to the cart
         cart.getItems().add(new CartItem(skuCode, quantity, price));
     }

     cart.setEmail(email);
     return cartRepository.save(cart);  // Save the cart in Redis
 }

 // Remove a product from the cart
 public Cart removeFromCart(String email, String skuCode) {
     Cart cart = getCart(email);

     cart.getItems().removeIf(item -> item.getSkuCode().equals(skuCode));

     return cartRepository.save(cart);  
 }

 // Empty the cart after the order is placed
 public void clearCart(String email) {
     cartRepository.deleteById(email);  
 }
}
