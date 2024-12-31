package com.venkat.ecommerce.orderservice.order;

import org.springframework.stereotype.Component;

import com.venkat.ecommerce.orderservice.orderline.OrderLineMapper;

@Component
public class OrderMapper {

    private OrderLineMapper orderLineMapper;

    public OrderMapper(OrderLineMapper orderLineMapper) {
        this.orderLineMapper = orderLineMapper;
    }
    
    public OrderDTO map(OrderDO orderDO) {
        return new OrderDTO(
            orderDO.getId(),
            orderDO.getTotalAmount(), 
            orderDO.getPaymentMethod(), 
            orderDO.getCustomerId(), 
            orderDO.getOrderLines().stream().map(orderLineMapper::map).toList());
    }

    public OrderDO map(OrderDTO orderDTO) {
        return OrderDO.builder()
            .id(orderDTO.id())
            .totalAmount(orderDTO.totalAmount())
            .paymentMethod(orderDTO.paymentMethod())
            .customerId(orderDTO.customerId())
            .orderLines(orderDTO.orderLines().stream().map(orderLineMapper::map).toList())
            .build();
    }
}
