package com.holovetskyi.groupexpensecalculator.participant.web.response;

import java.util.List;

import static java.util.Collections.emptyList;

public record UpdateParticipantResponse(boolean success, List<String> errors) {

    public static UpdateParticipantResponse SUCCESS = new UpdateParticipantResponse(true, emptyList());

}
