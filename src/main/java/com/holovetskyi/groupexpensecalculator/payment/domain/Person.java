package com.holovetskyi.groupexpensecalculator.payment.domain;

import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.entity.PaymentEntity;
import com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.entity.PersonEntity;
import com.holovetskyi.groupexpensecalculator.payment.web.dto.CreatePersonDTO;
import com.holovetskyi.groupexpensecalculator.payment.web.dto.GetPersonDTO;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public class Person {

    private Long id;
    private String firstName;

    private String lastName;

    private String email;

    private Set<EventEntity> events;

    private List<PaymentEntity> payment;

    public PersonEntity toPersonEntity(){
        return PersonEntity
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }

    public GetPersonDTO toGetPersonDTO(){
        return new GetPersonDTO(firstName, lastName, email);
    }

    public CreatePersonDTO toCreatePersonDTO() {
        return new CreatePersonDTO(id, firstName, lastName, email);
    }
}
