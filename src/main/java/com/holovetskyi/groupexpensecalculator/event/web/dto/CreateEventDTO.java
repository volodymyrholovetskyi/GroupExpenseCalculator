package com.holovetskyi.groupexpensecalculator.event.web.dto;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;

public record CreateEventDTO(Long id, String name) {
    public Event toEvent() {
       return Event.builder()
                .id(id)
                .name(name)
                .build();
    }
}
