package com.holovetskyi.groupexpensecalculator.event.web.dto;

import com.holovetskyi.groupexpensecalculator.event.domain.Currency;
import com.holovetskyi.groupexpensecalculator.event.domain.CurrentStatus;
import com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.entity.PersonEntity;

import java.time.LocalDateTime;
import java.util.Set;

public record GetEventDTO(Long id, String name, Currency currency, CurrentStatus status, LocalDateTime createAt, Set<PersonEntity> personEntities) {

}
