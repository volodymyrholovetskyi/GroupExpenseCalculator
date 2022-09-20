package com.holovetskyi.groupexpensecalculator.participant.web.dto;

import java.math.BigDecimal;

public record UpdatePaymentDTO(
        String name,
        BigDecimal payment) {
}
