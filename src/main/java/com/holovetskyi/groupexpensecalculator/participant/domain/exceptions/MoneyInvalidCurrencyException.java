package com.holovetskyi.groupexpensecalculator.participant.domain.exceptions;

public class MoneyInvalidCurrencyException extends RuntimeException{

    public MoneyInvalidCurrencyException(String message) {
        super(message);
    }
}
