package com.shankar.intelligentrestaurantmanagementsystem.mapper;

import com.shankar.intelligentrestaurantmanagementsystem.dto.CustomerDTO;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements Mapper<Customer, CustomerDTO, CustomerDTO> {

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .phone(customerDTO.getPhone())
                .address(customerDTO.getAddress())
                .build();
    }

    @Override
    public CustomerDTO toResponse(Customer entity) {
        return CustomerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .build();
    }
}
