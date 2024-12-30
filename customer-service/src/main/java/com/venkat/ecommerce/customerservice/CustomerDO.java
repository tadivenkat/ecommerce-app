package com.venkat.ecommerce.customerservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.venkat.ecommerce.customerservice.model.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDO {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
