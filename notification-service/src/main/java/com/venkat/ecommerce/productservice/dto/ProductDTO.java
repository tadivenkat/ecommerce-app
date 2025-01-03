package com.venkat.ecommerce.productservice.dto;

import java.math.BigDecimal;

public record ProductDTO(String id, String name, String description, BigDecimal price, Double availableQuantity) {
    
}