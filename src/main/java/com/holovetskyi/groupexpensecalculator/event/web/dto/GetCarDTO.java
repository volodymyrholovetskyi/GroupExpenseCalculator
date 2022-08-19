package com.holovetskyi.groupexpensecalculator.event.web.dto;

import com.holovetskyi.groupexpensecalculator.person.Person;

import java.util.Set;

public record GetCarDTO(

        String name,

        Set<Person> persons) {

}
