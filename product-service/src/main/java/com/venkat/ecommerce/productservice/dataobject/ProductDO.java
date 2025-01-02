package com.venkat.ecommerce.productservice.dataobject;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "products")
public class ProductDO {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double availableQuantity;
}
