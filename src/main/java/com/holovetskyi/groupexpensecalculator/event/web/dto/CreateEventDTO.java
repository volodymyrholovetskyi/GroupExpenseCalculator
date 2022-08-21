package com.holovetskyi.groupexpensecalculator.event.web.dto;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;

import javax.validation.constraints.Min;

public record CreateEventDTO(
        Long id,
        @Min(value = 2, message = "The name is too short")
        String name) {

    public Event toEvent() {
        return Event.builder()
                .id(id)
                .name(name)
                .build();
    }
}
