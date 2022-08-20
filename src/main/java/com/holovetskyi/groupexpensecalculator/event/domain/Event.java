package com.holovetskyi.groupexpensecalculator.event.domain;

import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.event.web.dto.CreateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.GetEventDTO;
import com.holovetskyi.groupexpensecalculator.person.Person;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Builder
@ToString
@EqualsAndHashCode
public class Event {

    private Long id;
    private String name;

    private Set<Person> persons;

    public EventEntity toEventEntity() {
        return EventEntity
                .builder()
                .name(name)
                .build();
    }

    public GetEventDTO toGetEventDTO() {
        return new GetEventDTO(id, name, persons);
    }

    public CreateEventDTO toCreateEventDTO() {
        return new CreateEventDTO(id, name);
    }
}