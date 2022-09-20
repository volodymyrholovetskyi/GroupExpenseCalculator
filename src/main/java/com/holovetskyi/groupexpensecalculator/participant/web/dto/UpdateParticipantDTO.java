package com.holovetskyi.groupexpensecalculator.participant.web.dto;

import com.holovetskyi.groupexpensecalculator.participant.domain.Participant;

public record UpdateParticipantDTO(
        String firstName,
        String lastName,
        String email

) {

    public Participant toParticipant(){
        return Participant
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }
}
