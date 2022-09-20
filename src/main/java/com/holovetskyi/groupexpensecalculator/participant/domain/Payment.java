package com.holovetskyi.groupexpensecalculator.participant.domain;

import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity.PaymentEntity;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.GetPaymentDTO;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public class Payment {

    private String name;

    private BigDecimal payment;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public PaymentEntity toPaymentEntity() {
        return PaymentEntity
                .builder()
                .name(name)
                .payment(payment)
                .build();
    }

    public GetPaymentDTO toGetPaymentDTO(){
        return new GetPaymentDTO(name, payment, createAt, updateAt);
    }
}
