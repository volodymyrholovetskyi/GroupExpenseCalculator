package com.holovetskyi.groupexpensecalculator.participant.web.response;

import java.util.List;

import static java.util.Collections.emptyList;

public record UpdatePaymentResponse(boolean success, List<String> errors) {

    public static UpdatePaymentResponse SUCCESS = new UpdatePaymentResponse(true,emptyList());
}
