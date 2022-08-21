package com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.impl;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.domain.repo.EventRepository;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.EntityRepositoryDao;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final EntityRepositoryDao repositoryDao;

    @Override
    public List<Event> findAll() {
        return repositoryDao.findAll()
                .stream()
                .map(EventEntity::toEvent)
                .toList();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return repositoryDao.findById(id).map(EventEntity::toEvent);
    }


    public Optional<EventEntity> findByIdEntity(Long id) {
       return repositoryDao.findById(id);
    }

    @Override
    public List<Event> findByNameStartsWithIgnoreCase(String name) {
        return null;
    }

    @Override
    public Event save(Event event) {
        EventEntity newEvent = repositoryDao.save(event.toEventEntity());
        return newEvent.toEvent();
    }

    @Override
    public void delete(Long id) {
        repositoryDao.deleteById(id);
    }
}
