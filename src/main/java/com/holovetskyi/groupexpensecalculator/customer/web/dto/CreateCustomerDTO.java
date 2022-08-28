package com.holovetskyi.groupexpensecalculator.customer.web.dto;

import com.holovetskyi.groupexpensecalculator.customer.domain.Customer;

public record CreateCustomerDTO(Long id, String firstName, String lastName, String email) {


    public Customer toCustomer() {
        return Customer
                .builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }
}
