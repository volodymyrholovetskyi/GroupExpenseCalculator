package com.holovetskyi.groupexpensecalculator.event.domain;

import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.event.web.dto.CreateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.GetEventDTO;
import com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.entity.PersonEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@ToString
@EqualsAndHashCode
public class Event {

    protected Long id;
    protected String name;

    Currency currency;
    CurrentStatus status;

    LocalDateTime createAt;
    Set<PersonEntity> personEntities;


    public EventEntity toEventEntity() {
        return EventEntity
                .builder()
                .name(name)
                .currency(currency)
                .build();
    }

    public GetEventDTO toGetEventDTO() {
        return new GetEventDTO(id, name, currency, status, createAt, personEntities);
    }

    public CreateEventDTO toCreateEventDTO() {
        return new CreateEventDTO(id, name);
    }
}