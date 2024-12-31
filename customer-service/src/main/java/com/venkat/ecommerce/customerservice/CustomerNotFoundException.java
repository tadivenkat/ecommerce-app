package com.venkat.ecommerce.customerservice;


public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        this("Customer not found");
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
