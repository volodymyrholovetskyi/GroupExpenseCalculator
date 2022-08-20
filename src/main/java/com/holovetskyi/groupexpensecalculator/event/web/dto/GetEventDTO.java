package com.holovetskyi.groupexpensecalculator.event.web.dto;

import com.holovetskyi.groupexpensecalculator.person.Person;

import java.util.Set;

public record GetEventDTO(Long id, String name, Set<Person> persons) {


}
