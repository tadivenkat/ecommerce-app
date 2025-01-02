package com.venkat.ecommerce.orderservice.config;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
            .requestFactory(getClientRequestFactory())
            .build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(CustomerServiceClient.class);
    }

    @Bean
    public ProductServiceClient productServiceClient() {
        RestClient restClient = RestClient.builder()
            .baseUrl(productServiceUrl)
            .requestFactory(getClientRequestFactory())
            .build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(ProductServiceClient.class);
    }

    private ClientHttpRequestFactory getClientRequestFactory() {
        // Configure request timeouts
        RequestConfig requestConfig = RequestConfig
                            .custom()
                            .setConnectionRequestTimeout(Timeout.ofSeconds(3))
                            .setResponseTimeout(Timeout.ofSeconds(5))
                            .build();

        // Create HttpClient with custom configuration
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
}
