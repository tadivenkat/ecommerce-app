package com.venkat.ecommerce.orderservice.client;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PutExchange;

import com.venkat.ecommerce.orderservice.dto.ProductDTO;
import com.venkat.ecommerce.orderservice.orderline.OrderLineDTO;

public interface ProductServiceClient {

    @PutExchange("/purchase")
    List<ProductDTO> purchaseProducts(@RequestBody List<OrderLineDTO> productSaleDTOs);
}
