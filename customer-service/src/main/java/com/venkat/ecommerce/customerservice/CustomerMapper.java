package com.venkat.ecommerce.customerservice;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    // Convert CustomerDO to CustomerDTO
    public CustomerDTO map(CustomerDO customerDO) {
        if (customerDO == null) {
            return null;
        }
        return new CustomerDTO(
                customerDO.getId(),
                customerDO.getFirstName(),
                customerDO.getLastName(),
                customerDO.getEmail(),
                customerDO.getAddress()
        );
    }

    // Convert CustomerDTO to CustomerDO
    public CustomerDO map(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        CustomerDO customerDO = new CustomerDO();
        customerDO.setId(customerDTO.id());
        customerDO.setFirstName(customerDTO.firstName());
        customerDO.setLastName(customerDTO.lastName());
        customerDO.setEmail(customerDTO.email());
        customerDO.setAddress(customerDTO.address());
        return customerDO;
    }
}