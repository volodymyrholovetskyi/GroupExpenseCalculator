package com.holovetskyi.groupexpensecalculator.participant.web.dto;

import com.holovetskyi.groupexpensecalculator.participant.domain.Payment;

import java.math.BigDecimal;

public record CreatePaymentDTO(
        String name,
        BigDecimal payment) {
    public Payment toPayment() {
        return Payment
                .builder()
                .name(name)
                .payment(payment)
                .build();
    }
}
