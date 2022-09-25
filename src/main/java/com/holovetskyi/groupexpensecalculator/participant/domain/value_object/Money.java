package com.holovetskyi.groupexpensecalculator.participant.domain.value_object;

import java.math.BigDecimal;

public class Money {

    private BigDecimal amount;
    private Currency currency;

    public Money (BigDecimal amount, Currency currency){
        this.amount = amount;
        this.currency = currency;
    }


}
