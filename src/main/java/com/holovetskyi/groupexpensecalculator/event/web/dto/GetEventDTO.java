package com.holovetskyi.groupexpensecalculator.event.web.dto;

import com.holovetskyi.groupexpensecalculator.event.domain.Currency;
import com.holovetskyi.groupexpensecalculator.event.domain.CurrentStatus;
import com.holovetskyi.groupexpensecalculator.customer.web.dto.GetCustomerDTO;

import java.time.LocalDateTime;
import java.util.Set;

public record GetEventDTO(
        Long id,
        String name,
        Currency currency,
        CurrentStatus status,
        LocalDateTime createAt,
        Set<GetCustomerDTO> persons) {

}
