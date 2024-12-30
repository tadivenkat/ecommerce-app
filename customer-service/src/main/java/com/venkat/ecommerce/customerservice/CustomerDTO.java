package com.venkat.ecommerce.customerservice;

import com.venkat.ecommerce.customerservice.model.Address;

public record CustomerDTO(String id, String firstName, String lastName, String email, Address address) {
    
}
