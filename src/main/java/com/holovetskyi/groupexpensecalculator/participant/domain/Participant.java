package com.holovetskyi.groupexpensecalculator.participant.domain;

import com.holovetskyi.groupexpensecalculator.participant.domain.value_object.Money;
import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity.ParticipantEntity;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.CreateParticipantDTO;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.GetParticipantDTO;
import lombok.Builder;

import java.util.Set;

@Builder
public class Participant {

    private Long id;
    private String firstName;

    private String lastName;

    private String email;

    private Set<Event> events;

    private Money money;
    public ParticipantEntity toParticipantEntity(){
        return ParticipantEntity
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }

    public GetParticipantDTO toParticipantDTO(){
        return new GetParticipantDTO(firstName, lastName, email, money);
    }


    public CreateParticipantDTO toCreateParticipantDTO() {
        return new CreateParticipantDTO(id, firstName, lastName, email);
    }
}
