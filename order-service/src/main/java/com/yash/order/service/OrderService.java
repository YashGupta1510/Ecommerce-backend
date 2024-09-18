package com.yash.order.service;

import com.yash.order.clients.InventoryClient;
import com.yash.order.clients.ProductClient;
import com.yash.order.clients.UserClient;
import com.yash.order.dtos.ProductDTO;
import com.yash.order.dtos.UserDTO;
import com.yash.order.entity.Order;
import com.yash.order.enums.OrderStatus;
import com.yash.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private InventoryClient inventoryClient;
    
    
    
    @Transactional
    public Order createOrder(String email, List<String> skuCodes, List<Integer> quantities) {
        // Fetch user info from User Service
        UserDTO user = userClient.getUserByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Calculate total price by fetching products from Product Service
        double totalPrice = 0;
        for (int i = 0; i < skuCodes.size(); i++) {
            String skuCode = skuCodes.get(i);
            int quantity = quantities.get(i);

            // Fetch product details from Product Service
            ProductDTO product = productClient.getProductBySkuCode(skuCode);

            // Check stock from Inventory Service using IsInStock endpoint
            boolean isInStock = inventoryClient.isInStock(skuCode, quantity);
            if (!isInStock) {
                throw new RuntimeException("Product out of stock: " + product .getName());
            }

            // Calculate total price
            totalPrice += product.getPrice() * quantity;
        }

        // Create and save the order with PENDING status
        Order order = new Order();
        order.setEmail(email);
        order.setProductIds(skuCodes);
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PENDING);  // Set the status as PENDING

        return orderRepository.save(order);
    }


    // Other methods (e.g., update order status)
}
