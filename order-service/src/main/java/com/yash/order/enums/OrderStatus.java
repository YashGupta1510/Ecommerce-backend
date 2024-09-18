package com.yash.order.enums;

public enum OrderStatus {
    PENDING,     // Order created but payment is pending
    CONFIRMED,   // Payment confirmed and order is being processed
    SHIPPED,     // Order has been shipped
    DELIVERED,   // Order has been delivered to the customer
    CANCELED     // Order was canceled
}
