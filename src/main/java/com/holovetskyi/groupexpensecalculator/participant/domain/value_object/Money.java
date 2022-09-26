package com.holovetskyi.groupexpensecalculator.participant.domain.value_object;

import com.holovetskyi.groupexpensecalculator.participant.domain.exceptions.MoneyInvalidAmountException;
import com.holovetskyi.groupexpensecalculator.participant.domain.exceptions.MoneyInvalidCurrencyException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public  final class Money {
    private static final List<String> VALID_CURRENCY_CODE = Arrays.asList("PLN", "USD", "HRN", "EURO");
    private final BigDecimal amount;
    private final String currency;

    private Money (BigDecimal amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }

    public Money addMoney(BigDecimal amount, String currency){
        if (!VALID_CURRENCY_CODE.contains(currency)){
            throw new MoneyInvalidAmountException("The currency must be one of %s".formatted(currency));
        }

        if (amount.compareTo(BigDecimal.ZERO) > 0){
            throw new MoneyInvalidCurrencyException("The value must be greater than zero");
        }

        return new Money(amount, currency);
    }
}
