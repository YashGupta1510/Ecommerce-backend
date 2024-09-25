package com.yash.order.entity;

import java.util.List;

import com.yash.order.dtos.CartItemDTO;
import com.yash.order.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private double totalPrice;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItemDTO> orderItems;

    private String paymentID;
    
    @Enumerated(EnumType.STRING)  // Store the enum as a string in the database
    private OrderStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


    public List<CartItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<CartItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

	public OrderStatus getOrderStatus() {
		return status;
	}

	public void setOrderStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", email=" + email + ", totalPrice=" + totalPrice + ", orderItems=" + orderItems
				+ ", status=" + status + "]";
	}

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	} 

    
    
}
