package com.holovetskyi.groupexpensecalculator.event.domain;

import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.event.web.dto.CreateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.GetEventDTO;
import com.holovetskyi.groupexpensecalculator.person.Person;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@ToString
@EqualsAndHashCode
public class Event {

    private Long id;
    private String name;

    private Currency currency;
    private CurrentStatus status;

    private LocalDateTime createAt;
    private Set<Person> persons;


    public EventEntity toEventEntity() {
        return EventEntity
                .builder()
                .name(name)
                .currency(currency)
                .build();
    }

    public GetEventDTO toGetEventDTO() {
        return new GetEventDTO(id, name, currency, status, createAt, persons);
    }

    public CreateEventDTO toCreateEventDTO() {
        return new CreateEventDTO(id, name);
    }
}