package com.venkat.ecommerce.orderservice.order;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.venkat.ecommerce.orderservice.client.CustomerServiceClient;
import com.venkat.ecommerce.orderservice.client.ProductServiceClient;

@Service
public class OrderService {
    
    private final CustomerServiceClient customerServiceClient;
    private final ProductServiceClient productServiceClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(CustomerServiceClient customerServiceClient, ProductServiceClient productServiceClient, OrderRepository orderRepository, OrderMapper orderMapper) {
        this.customerServiceClient = customerServiceClient;
        this.productServiceClient = productServiceClient;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }


    public OrderDTO createOrder(OrderDTO orderDTO) {
        // Validate the customerId by calling the CustomerServiceClient
        try {
            customerServiceClient.getCustomer(orderDTO.customerId());
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Customer not found");
        }

        // Purchase the products by calling the ProductServiceClient
        try {
            productServiceClient.purchaseProducts(orderDTO.orderLines());
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Product not found or not enough quantity available");
        }

        // Create the order
        return orderMapper.map(orderRepository.save(orderMapper.map(orderDTO)));

        //TODO: Send the notification to the NotificationService
    }
}
