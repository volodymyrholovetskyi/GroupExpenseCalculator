package com.holovetskyi.groupexpensecalculator.participant.web;

import com.holovetskyi.groupexpensecalculator.participant.application.ParticipantService;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.CreateParticipantDTO;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.GetParticipantDTO;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.UpdateParticipantDTO;
import com.holovetskyi.groupexpensecalculator.participant.web.response.UpdateParticipantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("participants")
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService service;

    @GetMapping
    @ResponseStatus(OK)
    List<GetParticipantDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    ResponseEntity<Void> addParticipant(@Valid @RequestBody CreateParticipantDTO participantDTO) {
        System.out.println(participantDTO);
        CreateParticipantDTO createParticipantDTO = service.addParticipant(participantDTO);
        CreatedParticipantURI uri = new CreatedParticipantURI("/" + createParticipantDTO.id().toString());
        return ResponseEntity.created(uri.createdParticipantUri()).build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(ACCEPTED)
    void update(@PathVariable Long id, @RequestBody UpdateParticipantDTO participantDTO) {
        UpdateParticipantResponse response = service.updateParticipant(id, participantDTO);

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
