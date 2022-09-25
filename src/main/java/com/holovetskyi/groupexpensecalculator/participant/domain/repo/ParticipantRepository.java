package com.holovetskyi.groupexpensecalculator.participant.domain.repo;

import com.holovetskyi.groupexpensecalculator.participant.domain.Participant;
import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity.ParticipantEntity;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository {

    List<Participant> findAll();

    Optional<Participant> findById(Long id);

    Optional<ParticipantEntity> findByIdParticipantEntity(Long id);
    Participant save(Participant participant);

    void delete(Long id);
}
