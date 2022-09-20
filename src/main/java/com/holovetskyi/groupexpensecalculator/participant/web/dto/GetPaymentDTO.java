package com.holovetskyi.groupexpensecalculator.participant.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetPaymentDTO(
        String name,

        BigDecimal payment,

        LocalDateTime createAt,

        LocalDateTime updateAt) {

}
