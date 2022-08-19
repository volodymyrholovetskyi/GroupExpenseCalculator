package com.holovetskyi.groupexpensecalculator.event.domain;

import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.person.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@EqualsAndHashCode
public class Event {

    private Long id;
    private String name;

    private Set<Person> persons;

    public EventEntity toEventEntity() {
        return EventEntity
                .builder()
                .id(id)
                .name(name)
                .build();
    }
}