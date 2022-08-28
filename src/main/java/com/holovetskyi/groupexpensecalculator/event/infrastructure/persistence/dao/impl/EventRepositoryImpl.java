package com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.impl;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.domain.repo.EventRepository;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.EntityRepositoryDAO;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final EntityRepositoryDAO repositoryDAO;

    @Override
    public List<Event> findAll() {
        return repositoryDAO.findAll()
                .stream()
                .map(EventEntity::toEvent)
                .toList();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return repositoryDAO.findById(id).map(EventEntity::toEvent);
    }


    public Optional<EventEntity> findByIdEventEntity(Long id) {
       return repositoryDAO.findById(id);
    }

    @Override
    public List<Event> findByNameStartsWithIgnoreCase(String name) {
       return repositoryDAO.findByNameStartsWithIgnoreCase(name)
                .stream()
                .map(EventEntity::toEvent)
                .toList();
            }

    @Override
    public Event save(Event event) {
        EventEntity newEvent = repositoryDAO.save(event.toEventEntity());
        return newEvent.toEvent();
    }

    @Override
    public void delete(Long id) {
        repositoryDAO.deleteById(id);
    }
}
