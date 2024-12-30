package com.venkat.ecommerce.customerservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRespository extends MongoRepository<CustomerDO, String> {
    
}
