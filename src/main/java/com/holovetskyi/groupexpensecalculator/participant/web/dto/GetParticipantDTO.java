package com.holovetskyi.groupexpensecalculator.participant.web.dto;

import java.util.List;

public record GetParticipantDTO(String firstName, String lastName, String email, List<GetPaymentDTO> payment) {
}
