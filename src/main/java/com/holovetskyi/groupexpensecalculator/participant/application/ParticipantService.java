package com.holovetskyi.groupexpensecalculator.participant.application;

import com.holovetskyi.groupexpensecalculator.participant.domain.Participant;
import com.holovetskyi.groupexpensecalculator.participant.domain.value_object.Money;
import com.holovetskyi.groupexpensecalculator.participant.domain.repo.ParticipantRepository;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.CreateParticipantDTO;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.GetParticipantDTO;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.UpdateParticipantDTO;
import com.holovetskyi.groupexpensecalculator.participant.web.response.UpdateParticipantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public List<GetParticipantDTO> getAll() {
        return participantRepository.findAll()
                .stream()
                .map(Participant::toParticipantDTO)
                .toList();
    }

    public CreateParticipantDTO addParticipant(CreateParticipantDTO participantDTO) {
        Participant participant = participantRepository.save(participantDTO.toParticipant());
        return participant.toCreateParticipantDTO();
    }

    @Transactional
    public UpdateParticipantResponse updateParticipant(Long id, UpdateParticipantDTO participantDTO) {
        return participantRepository
                .findByIdParticipantEntity(id)
                .map(participant -> {
                    participant.updateFields(participantDTO);
                    return UpdateParticipantResponse.SUCCESS;
                })
                .orElseGet(() -> new UpdateParticipantResponse(false,
                        Collections.singletonList("Participant not found with id: " + id)));
    }

    public void deleteById(Long id) {
        participantRepository.delete(id);
    }


//    private CustomerEntity findByIdCustomerEntity(Long id) {
//        return customerRepository.findByIdCustomerEntity(id)
//                .orElseThrow(() -> new IllegalArgumentException("Unable to find customer with id: " + id));
//    }
}