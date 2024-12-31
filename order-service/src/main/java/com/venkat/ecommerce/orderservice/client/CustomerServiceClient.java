package com.venkat.ecommerce.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.venkat.ecommerce.orderservice.dto.CustomerDTO;

@FeignClient(name = "customer-service", url = "${service.customer-service.url}")
public interface CustomerServiceClient {
    @GetMapping("/{customerId}")
    CustomerDTO getCustomer(@PathVariable("customerId") String customerId);    
}