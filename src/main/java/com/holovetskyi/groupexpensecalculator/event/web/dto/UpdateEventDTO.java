package com.holovetskyi.groupexpensecalculator.event.web.dto;

import com.holovetskyi.groupexpensecalculator.event.domain.Currency;

public record UpdateEventDTO(Long id, String name, Currency currency) {

}
