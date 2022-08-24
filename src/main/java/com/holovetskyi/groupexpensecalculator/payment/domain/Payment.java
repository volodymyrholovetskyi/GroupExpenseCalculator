package com.holovetskyi.groupexpensecalculator.payment.domain;

import com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.entity.PersonEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private String namePayment;

    private BigDecimal payment;

    private PersonEntity personEntity;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
