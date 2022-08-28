package com.holovetskyi.groupexpensecalculator.customer.domain;

import com.holovetskyi.groupexpensecalculator.customer.infrastructure.persistence.entity.CustomerEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private String namePayment;

    private BigDecimal payment;

    private CustomerEntity customerEntity;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
