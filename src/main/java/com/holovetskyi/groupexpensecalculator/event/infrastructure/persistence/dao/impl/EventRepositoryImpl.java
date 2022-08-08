package com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.impl;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.domain.repo.EventRepository;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.EntityRepositoryDao;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EntityRepositoryImpl implements EntityRepository {

    private final EntityRepositoryDao repositoryDao;

    @Override
    public Optional<Entity> save() {
        return null;
    }

    @Override
    public List<Entity> findAll() {
        return null;
    }

    @Override
    public Optional<Entity> findById(Long id) {
        return null;
    }
}
