package com.venkat.ecommerce.orderservice.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.venkat.ecommerce.orderservice.dto.CustomerDTO;

public interface CustomerServiceClient {
    @GetExchange("/{customerId}")
    CustomerDTO getCustomer(@PathVariable("customerId") String customerId);    
}