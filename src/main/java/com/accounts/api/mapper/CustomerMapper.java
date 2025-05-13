package com.accounts.api.mapper;

import com.accounts.api.dto.CustomerDto;
import com.accounts.api.entity.Customer;

public class CustomerMapper {

    public static Customer toCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .mobileNumber(customerDto.getMobileNumber()).
                build();
    }

    public static CustomerDto mapToCustomerDto(Customer customer) {
        return CustomerDto.builder().
                name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .build();
    }

    public static void mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
    }
}
