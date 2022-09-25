package com.holovetskyi.groupexpensecalculator.event.web.dto;

import com.holovetskyi.groupexpensecalculator.event.domain.Currency;
import com.holovetskyi.groupexpensecalculator.event.domain.CurrentStatus;
import com.holovetskyi.groupexpensecalculator.event.domain.Event;

public record UpdateEventDTO(String name, Currency currency, CurrentStatus status) {

    public Event toEvent(){
        return Event
                .builder()
                .name(name)
                .currency(currency)
                .status(status)
                .build();
    }

}
