package com.holovetskyi.groupexpensecalculator.event.web;

import lombok.AllArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
public class CreatedEventURI {

    private final String path;

    public URI createdEventUri() {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(path)
                .build()
                .toUri();
    }
}
