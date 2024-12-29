package com.venkat.ecommerce.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.venkat.ecommerce.productservice.dataobject.ProductDO;

public interface ProductRepository extends MongoRepository<ProductDO, String> {
    
}
