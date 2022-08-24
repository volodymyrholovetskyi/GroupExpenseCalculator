package com.holovetskyi.groupexpensecalculator.payment.web.dto;

import com.holovetskyi.groupexpensecalculator.payment.domain.Person;

public record CreatePersonDTO (Long id, String firstName, String lastName, String email) {


    public Person toPerson() {
        return Person
                .builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }
}
