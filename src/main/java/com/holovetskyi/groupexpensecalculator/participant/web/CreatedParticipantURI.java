package com.holovetskyi.groupexpensecalculator.participant.web;

import lombok.AllArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
public class CreatedParticipantURI {
    private final String path;
    public URI createdParticipantUri() {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(path)
                .build()
                .toUri();
    }
}
