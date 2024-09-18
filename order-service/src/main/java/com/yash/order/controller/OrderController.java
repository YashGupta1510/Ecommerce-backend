package com.yash.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.order.entity.Order;
import com.yash.order.enums.OrderStatus;
import com.yash.order.service.OrderService;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Create a new order using the items in the user's cart
    @PostMapping("/{email}")
    public Order createOrder(@PathVariable String email) {
        return orderService.createOrderFromCart(email);
    }

    // Get all orders for a specific user by email
    @GetMapping("/{email}")
    public List<Order> getUserOrders(@PathVariable String email) {
        return orderService.getOrdersByEmail(email);
    }

    // Update order status (for example, from PENDING to CONFIRMED)
    @PutMapping("/{orderId}/status")
    public Order updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status) {
        return orderService.updateOrderStatus(orderId, status);
    }
}
