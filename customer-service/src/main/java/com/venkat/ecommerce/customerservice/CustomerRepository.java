package com.venkat.ecommerce.customerservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerDO, String> {
    
}
