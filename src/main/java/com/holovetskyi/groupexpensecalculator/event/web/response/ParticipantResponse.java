package com.holovetskyi.groupexpensecalculator.event.web.response;

import java.util.List;

import static java.util.Collections.emptyList;

public record ParticipantResponse(boolean success, List<String> errors) {

    public static UpdateEventResponse SUCCESS = new UpdateEventResponse(true, emptyList());

}