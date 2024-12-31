package com.venkat.ecommerce.orderservice.order;

import java.math.BigDecimal;
import java.util.List;

import com.venkat.ecommerce.orderservice.orderline.OrderLineDTO;

public record OrderDTO(
    Integer id,
    BigDecimal totalAmount, 
    PaymentMethod paymentMethod, 
    String customerId, 
    List<OrderLineDTO> orderLines) {
    
}
