package com.yash.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.order.clients.CartClient;
import com.yash.order.clients.InventoryClient;
import com.yash.order.clients.UserClient;
import com.yash.order.dtos.CartDTO;
import com.yash.order.dtos.CartItemDTO;
import com.yash.order.dtos.InventoryUpdateDTO;
import com.yash.order.dtos.InventoryUpdateDTO.Action;
import com.yash.order.dtos.UserDTO;
import com.yash.order.entity.Order;
import com.yash.order.enums.OrderStatus;
import com.yash.order.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartClient cartClient;

	@Autowired
	private UserClient userClient;

	@Autowired
	private InventoryClient inventoryClient;

	@Transactional
	public Order createOrderFromCart(String email, String paymentID) {
		UserDTO user = userClient.getUserByEmail(email);
		if (user == null) {
			throw new RuntimeException("User not found");
		}
		CartDTO cartDTO = cartClient.getCart(email);

		List<CartItemDTO> cartItems = cartDTO.getItems();

		double totalPrice = 0;
		for (CartItemDTO cartItem : cartItems) {

			String skuCode = cartItem.getSkuCode();
			int quantity = cartItem.getQuantity();
			double price = cartItem.getPrice();

			// Check stock from Inventory Service using IsInStock endpoint
			boolean isInStock = inventoryClient.isInStock(skuCode, quantity);
			
			inventoryClient.updateQuantity(skuCode, new InventoryUpdateDTO(quantity, Action.DECREASE));
			if (!isInStock) {
				throw new RuntimeException("Product out of stock: " + skuCode);
			}

			// Calculate total price
			totalPrice += price * quantity;
		}

		// Create and save the order with PENDING status
		Order order = new Order();
		order.setEmail(email);
		order.setPaymentID(paymentID);
		order.setOrderItems(cartItems);
		order.setTotalPrice(totalPrice);
		order.setOrderStatus(OrderStatus.PENDING);
		
		cartClient.clearCart(email);
		return orderRepository.save(order);
	}

	// Get all orders for a specific user by email
	public List<Order> getOrdersByEmail(String email) {
		return orderRepository.findByEmail(email);
	}

	// Create order after successful payment
//	@Transactional
//	public Order createOrderAfterPayment(String orderId, String paymentId, String email) {
//		// Implement logic to create the order using the provided email and payment ID
//		UserDTO user = userClient.getUserByEmail(email);
//		if (user == null) {
//			throw new RuntimeException("User not found");
//		}
//		CartDTO cartDTO = cartClient.getCart(email);
//
//		List<CartItemDTO> cartItems = cartDTO.getItems();
//
//		double totalPrice = 0;
//		for (CartItemDTO cartItem : cartItems) {
//
//			String skuCode = cartItem.getSkuCode();
//			int quantity = cartItem.getQuantity();
//			double price = cartItem.getPrice();
//
//			// Check stock from Inventory Service using IsInStock endpoint
//			boolean isInStock = inventoryClient.isInStock(skuCode, quantity);
//			if (!isInStock) {
//				throw new RuntimeException("Product out of stock: " + skuCode);
//			}
//
//			// Calculate total price
//			totalPrice += price * quantity;
//		}
//
//		// Create and save the order with PENDING status
//		Order order = new Order();
//		order.setEmail(email);
//		order.setOrderItems(cartItems);
//		order.setTotalPrice(totalPrice);
//		order.setPaymentID(paymentId);
//		order.setOrderStatus(OrderStatus.CONFIRMED);
//
//		return orderRepository.save(order);
//	}

	// Update the status of an order
	@Transactional
	public Order updateOrderStatus(Long orderId, OrderStatus status) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		order.setOrderStatus(status);
		return orderRepository.save(order);
	}
}
