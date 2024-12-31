package com.venkat.ecommerce.customerservice;

import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        CustomerDO customerDO = customerMapper.map(customerDTO);
        logger.debug("Customer to be created:{}", customerDO);
        CustomerDO savedCustomer = customerRepository.save(customerDO);
        logger.info("Customer created with id:{}", savedCustomer.getId());
        return customerMapper.map(savedCustomer);
    }

    public CustomerDTO getCustomer(String id) {
        return customerMapper.map(customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new));
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
        logger.info("Customer deleted with id:{}", id);
    }

    public CustomerDTO updateCustomer(String id, CustomerDTO customerDTO) {
        Optional<CustomerDO> customerDOOptional = customerRepository.findById(id);
        if (!customerDOOptional.isPresent()) {
            throw new IllegalArgumentException("Customer not found for id:" + id);
        }
        CustomerDO currentCustomerDO = customerDOOptional.get();
        if (StringUtils.isNotEmpty(customerDTO.firstName())) {
            currentCustomerDO.setFirstName(customerDTO.firstName());
        }
        if (StringUtils.isNotEmpty(customerDTO.lastName())) {
            currentCustomerDO.setLastName(customerDTO.lastName());
        }
        if (StringUtils.isNotEmpty(customerDTO.email())) {
            currentCustomerDO.setEmail(customerDTO.email());
        }
        if (customerDTO.address() != null) {
            currentCustomerDO.setAddress(customerDTO.address());
        }
        CustomerDO updatedCustomerDO = customerRepository.save(currentCustomerDO);
        logger.info("Customer updated with id:{}", id);
        return customerMapper.map(updatedCustomerDO);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::map).collect(Collectors.toList());
    }
}
