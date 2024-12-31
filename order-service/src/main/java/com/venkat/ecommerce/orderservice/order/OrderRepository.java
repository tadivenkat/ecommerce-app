package com.venkat.ecommerce.orderservice.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDO, Integer> {
    
}
