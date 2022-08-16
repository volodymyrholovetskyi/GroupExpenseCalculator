package com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.impl;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.domain.repo.EventRepository;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.EntityRepositoryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final EntityRepositoryDao repositoryDao;

    @Override
    public Optional<Event> save() {
        return repositoryDao.save();
    }

    @Override
    public List<Event> findAll() {
        return null;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return null;
    }
}
