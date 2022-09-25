package com.holovetskyi.groupexpensecalculator.event.domain;

import com.holovetskyi.groupexpensecalculator.event.domain.type.CurrentStatus;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.event.web.dto.CreateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.GetEventDTO;
import com.holovetskyi.groupexpensecalculator.participant.domain.Participant;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.GetParticipantDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@ToString
@EqualsAndHashCode
public class Event {

    protected Long id;

    protected String name;
    protected CurrentStatus status;
    protected LocalDateTime createAt;
    protected Set<Participant> participants;

    public EventEntity toEventEntity() {
        return EventEntity
                .builder()
                .name(name)
                .build();
    }

    public GetEventDTO toGetEventDTO() {
        return new GetEventDTO(id, name, status, createAt, toCustomerSet());
    }

    public CreateEventDTO toCreateEventDTO() {
        return new CreateEventDTO(id, name);
    }

    private Set<GetParticipantDTO> toCustomerSet(){
        return participants
                .stream()
                .map(Participant::toParticipantDTO)
                .collect(Collectors.toSet());
    }
}