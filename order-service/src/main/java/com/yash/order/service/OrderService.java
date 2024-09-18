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
    public Order createOrderFromCart(String email) {
        // Fetch user info from User Service
        UserDTO user = userClient.getUserByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        CartDTO cartDTO = cartClient.getCart(email);
        
        List<CartItemDTO> cartItems = cartDTO.getItems();
        
        // Calculate total price by fetching products from Product Service
        double totalPrice = 0;
        for (CartItemDTO cartItem : cartItems) {
        	
            String skuCode = cartItem.getSkuCode();
            int quantity = cartItem.getQuantity();
            double price = cartItem.getPrice();

            // Check stock from Inventory Service using IsInStock endpoint
            boolean isInStock = inventoryClient.isInStock(skuCode, quantity);
            if (!isInStock) {
                throw new RuntimeException("Product out of stock: " + skuCode);
            }

            // Calculate total price
            totalPrice += price * quantity;
        }

        // Create and save the order with PENDING status
        Order order = new Order();
        order.setEmail(email);
        order.setOrderItems(cartItems);
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PENDING);

        return orderRepository.save(order);
    }

    // Get all orders for a specific user by email
    public List<Order> getOrdersByEmail(String email) {
        return orderRepository.findByEmail(email);
    }

    // Update the status of an order
    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        return orderRepository.save(order);
    }
}
