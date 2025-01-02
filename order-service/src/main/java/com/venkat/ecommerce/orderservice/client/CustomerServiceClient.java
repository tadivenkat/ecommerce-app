package com.venkat.ecommerce.orderservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.venkat.ecommerce.orderservice.dto.CustomerDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

public interface CustomerServiceClient {
    public static final Logger logger = LoggerFactory.getLogger(CustomerServiceClient.class);
    @GetExchange("/{customerId}")
    @CircuitBreaker(name = "customer-service", fallbackMethod = "getCustomerFallback")
    @Retry(name = "customer-service", fallbackMethod = "getCustomerFallback")
    CustomerDTO getCustomer(@PathVariable("customerId") String customerId);    

    default CustomerDTO getCustomerFallback(String customerId) {
        logger.error("Customer service is not available");
        return new CustomerDTO("id", "firstName", "lastName", "email", null);
    }
}