package com.yash.order.repository;

import com.yash.order.entity.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
	// Custom query to find orders by email
    List<Order> findByEmail(String email);
}
