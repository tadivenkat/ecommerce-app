package com.venkat.ecommerce.orderservice.orderline;

import com.venkat.ecommerce.orderservice.order.OrderDO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "order_lines")
@Data
@Builder
public class OrderLineDO {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderDO order;

    private String productId;

    private Double quantity;
    
}
