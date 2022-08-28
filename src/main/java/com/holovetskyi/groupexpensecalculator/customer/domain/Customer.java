package com.holovetskyi.groupexpensecalculator.customer.domain;

import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.customer.infrastructure.persistence.entity.PaymentEntity;
import com.holovetskyi.groupexpensecalculator.customer.infrastructure.persistence.entity.CustomerEntity;
import com.holovetskyi.groupexpensecalculator.customer.web.dto.CreateCustomerDTO;
import com.holovetskyi.groupexpensecalculator.customer.web.dto.GetCustomerDTO;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public class Customer {

    private Long id;
    private String firstName;

    private String lastName;

    private String email;

    private Set<EventEntity> events;

    private List<PaymentEntity> payment;

    public CustomerEntity toCustomerEntity(){
        return CustomerEntity
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }

    public GetCustomerDTO toGetCustomerDTO(){
        return new GetCustomerDTO(firstName, lastName, email);
    }

    public CreateCustomerDTO toCreateCustomerDTO() {
        return new CreateCustomerDTO(id, firstName, lastName, email);
    }
}
