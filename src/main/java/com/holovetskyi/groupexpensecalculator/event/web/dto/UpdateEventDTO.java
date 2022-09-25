package com.holovetskyi.groupexpensecalculator.event.web.dto;

import com.holovetskyi.groupexpensecalculator.event.domain.type.CurrentStatus;
import com.holovetskyi.groupexpensecalculator.event.domain.Event;

public record UpdateEventDTO(String name, CurrentStatus status) {

    public Event toEvent(){
        return Event
                .builder()
                .name(name)
                .status(status)
                .build();
    }

}
