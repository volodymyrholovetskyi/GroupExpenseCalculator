package com.holovetskyi.groupexpensecalculator.event.web;

import com.holovetskyi.groupexpensecalculator.event.application.EventService;
import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.event.web.dto.GetEventDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("events")
public class EventController {

    private final EventService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<GetEventDTO> getAll(@RequestParam Optional<String> name) {
        if (name.isPresent()) {
            return service.findByName(name.get());
        }
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




}
