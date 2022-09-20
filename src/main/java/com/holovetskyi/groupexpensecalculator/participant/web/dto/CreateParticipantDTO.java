package com.holovetskyi.groupexpensecalculator.participant.web.dto;

import com.holovetskyi.groupexpensecalculator.participant.domain.Participant;

public record CreateParticipantDTO(Long id, String firstName, String lastName, String email) {


    public Participant toParticipant() {
        return Participant
                .builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }
}
