package com.venkat.ecommerce.orderservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.venkat.ecommerce.orderservice.dto.ProductDTO;
import com.venkat.ecommerce.orderservice.orderline.OrderLineDTO;

@FeignClient(name = "product-service", url = "${service.product-service.url}")
public interface ProductServiceClient {

    @PutMapping("/purchase")
    List<ProductDTO> purchaseProducts(@RequestBody List<OrderLineDTO> productSaleDTOs);
}
