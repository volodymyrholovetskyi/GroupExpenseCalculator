package com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.dao.impl;

import com.holovetskyi.groupexpensecalculator.participant.domain.Participant;
import com.holovetskyi.groupexpensecalculator.participant.domain.repo.ParticipantRepository;
import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.dao.ParticipantRepositoryDAO;
import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity.ParticipantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ParticipantRepositoryImpl implements ParticipantRepository {

    private final ParticipantRepositoryDAO repositoryDAO;

    @Override
    public List<Participant> findAll() {
        return repositoryDAO.findAll()
                .stream()
                .map(ParticipantEntity::toParticipant)
                .toList();
    }

    @Override
    public Optional<Participant> findById(Long id) {
        return repositoryDAO.findById(id).map(ParticipantEntity::toParticipant);
    }

    public Optional<ParticipantEntity> findByIdParticipantEntity(Long id) {
        return repositoryDAO.findById(id);
    }

    @Override
    public Participant save(Participant newParticipant) {
        ParticipantEntity person = repositoryDAO.save(newParticipant.toParticipantEntity());
        return person.toParticipant();
    }

    @Override
    public void delete(Long id) {
        repositoryDAO.deleteById(id);
    }
}
