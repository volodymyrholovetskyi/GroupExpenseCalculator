package com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.dao;

import com.holovetskyi.groupexpensecalculator.participant.domain.Participant;
import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepositoryDAO extends JpaRepository<ParticipantEntity, Long> {

    Participant save(Participant event);

    List<ParticipantEntity> findAll();

    Optional<ParticipantEntity> findById(Long id);

    void deleteById(Long id);
}
