package com.venkat.ecommerce.orderservice.orderline;

import org.springframework.stereotype.Component;
import com.venkat.ecommerce.orderservice.order.OrderDO;

@Component
public class OrderLineMapper {
    public OrderLineDTO map(OrderLineDO orderLineDO) {
        return new OrderLineDTO(
            orderLineDO.getId(), 
            orderLineDO.getOrder().getId(), 
            orderLineDO.getProductId(), 
            orderLineDO.getQuantity()
        );
    }

    public OrderLineDO map(OrderLineDTO orderLineDTO) {
        return OrderLineDO.builder()
            .id(orderLineDTO.id())
            .order(OrderDO.builder().id(orderLineDTO.orderId()).build())
            .productId(orderLineDTO.productId())
            .quantity(orderLineDTO.quantity())
            .build();
    }
}
