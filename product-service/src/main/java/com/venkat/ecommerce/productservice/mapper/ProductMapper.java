package com.venkat.ecommerce.productservice.mapper;

import org.springframework.stereotype.Component;

import com.venkat.ecommerce.productservice.dataobject.ProductDO;
import com.venkat.ecommerce.productservice.dto.ProductDTO;

@Component
public class ProductMapper {
    public ProductDO map(ProductDTO productDTO) {
        ProductDO productDO = ProductDO.builder()
                                        .id(productDTO.id())
                                        .name(productDTO.name())
                                        .description(productDTO.description())
                                        .price(productDTO.price())
                                        .availableQuantity(productDTO.availableQuantity())
                                        .build();
        return productDO;
    }

    public ProductDTO map(ProductDO productDO) {
        ProductDTO productDTO = new ProductDTO(
                                        productDO.getId(), 
                                        productDO.getName(), 
                                        productDO.getDescription(), 
                                        productDO.getPrice(), 
                                        productDO.getAvailableQuantity());
        return productDTO;
    }
}
