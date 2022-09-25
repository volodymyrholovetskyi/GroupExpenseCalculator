package com.holovetskyi.groupexpensecalculator.participant.domain.value_object;

public class Currency {

    private String currency;

    public Currency(String currency){
        this.currency = currency;
    }

    public static Currency getPLN(){
        return new Currency("PLN");
    }

    public static Currency getUSD(){
        return new Currency("USD");
    }

    public static Currency getHRN(){
        return new Currency("HRN");
    }

//    PLN, USD, HRN, EURO
}
