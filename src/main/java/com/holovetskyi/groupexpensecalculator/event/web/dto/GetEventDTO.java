package com.holovetskyi.groupexpensecalculator.event.web.dto;

import com.holovetskyi.groupexpensecalculator.event.domain.type.CurrentStatus;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.GetParticipantDTO;

import java.time.LocalDateTime;
import java.util.Set;

public record GetEventDTO(
        Long id,
        String name,
        CurrentStatus status,
        LocalDateTime createAt,
        Set<GetParticipantDTO> persons) {

}
