package com.holovetskyi.groupexpensecalculator.customer.web;

import lombok.AllArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
public class CreatedCustomerURI {

    private final String path;

    public URI createdPersonUri() {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(path)
                .build()
                .toUri();
    }
}
