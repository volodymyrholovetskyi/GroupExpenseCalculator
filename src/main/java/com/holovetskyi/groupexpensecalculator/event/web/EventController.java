package com.holovetskyi.groupexpensecalculator.event.web;

import com.holovetskyi.groupexpensecalculator.event.application.EventService;
import com.holovetskyi.groupexpensecalculator.event.web.dto.CreateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.GetEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.ResourceIdsDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.UpdateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.response.UpdateEventResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("events")
public class EventController {

    private final EventService service;

    @ResponseStatus(OK)
    @GetMapping
    List<GetEventDTO> getAll(@RequestParam Optional<String> name) {
        if (name.isPresent()) {
            return service.findByName(name.get());
        }
        return service.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Void> addEvent(@Valid @RequestBody CreateEventDTO eventDTO) {
        CreateEventDTO createEventDTO = service.addEvent(eventDTO);
        CreatedEntityURI uri = new CreatedEntityURI("/" + createEventDTO.id().toString());
        return ResponseEntity.created(uri.createdEventUri()).build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(ACCEPTED)
    void update(@PathVariable Long id, @RequestBody UpdateEventDTO eventDTO) {
        UpdateEventResponse response = service.updateEvent(id, eventDTO);

        if (!response.success()) {
            String message = String.join(", ", response.errors());
            throw new ResponseStatusException(BAD_REQUEST, message);
        }
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addPersonToEvent(@PathVariable Long id, @RequestBody ResourceIdsDTO persons) {
        UpdateEventResponse response = service.updateEventPerson(id, persons);

        if (!response.success()) {
            String message = String.join(", ", response.errors());
            throw new ResponseStatusException(BAD_REQUEST, message);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
