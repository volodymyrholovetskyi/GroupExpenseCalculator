package com.holovetskyi.groupexpensecalculator.participant.domain.exceptions;

public class MoneyInvalidAmountException extends RuntimeException {

    public MoneyInvalidAmountException(String message){
        super(message);
    }
}
