package com.holovetskyi.groupexpensecalculator.event.domain;

import com.holovetskyi.groupexpensecalculator.person.Person;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Event {

    private String name;

    private Set<Person> persons;

    private CurrentStatus status;

    private LocalDateTime createAt;

}
