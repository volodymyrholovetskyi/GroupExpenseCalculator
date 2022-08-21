package com.holovetskyi.groupexpensecalculator.event.web.dto;

import com.holovetskyi.groupexpensecalculator.event.domain.Currency;

public record PutEventDTO(Long id, String name, Currency currency) {
}
