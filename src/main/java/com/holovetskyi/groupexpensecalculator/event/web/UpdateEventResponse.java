package com.holovetskyi.groupexpensecalculator.event.web;

import java.util.List;

import static java.util.Collections.emptyList;

public record UpdateEventResponse(boolean success, List<String> errors) {

    public static UpdateEventResponse SUCCESS = new UpdateEventResponse(true, emptyList());

}
