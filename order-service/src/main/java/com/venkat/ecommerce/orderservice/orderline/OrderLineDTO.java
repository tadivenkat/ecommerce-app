package com.venkat.ecommerce.orderservice.orderline;

public record OrderLineDTO(Integer id, Integer orderId, String productId, Double quantity) {
    
}
