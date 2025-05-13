package com.accounts.api.mapper;

import com.accounts.api.dto.CustomerDto;
import com.accounts.api.entity.Customer;

public class CustomerMapper {

    public static Customer toCustomerDto(CustomerDto customerDto) {
        return Customer.builder().name(customerDto.getName()).email(customerDto.getEmail()).mobileNumber(customerDto.getMobileNumber()).build();
    }
}
