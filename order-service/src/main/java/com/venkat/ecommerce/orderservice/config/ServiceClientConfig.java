package com.venkat.ecommerce.orderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.venkat.ecommerce.orderservice.client.CustomerServiceClient;
import com.venkat.ecommerce.orderservice.client.ProductServiceClient;

public class ServiceClientConfig {
    @Value("${service.customer-service.url}")
    private String customerServiceUrl;

    @Value("${service.product-service.url}")
    private String productServiceUrl;
    
    @Bean
    public CustomerServiceClient customerServiceClient() {
        RestClient restClient = RestClient.builder()
            .baseUrl(customerServiceUrl)
            .build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(CustomerServiceClient.class);
    }

    @Bean
    public ProductServiceClient productServiceClient() {
        RestClient restClient = RestClient.builder()
            .baseUrl(productServiceUrl)
            .build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(ProductServiceClient.class);
    }
}
