package com.holovetskyi.groupexpensecalculator.event.domain.repo;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    List<Event> findAll();

    Optional<Event> findById(Long id);

    Optional<EventEntity> findByIdEventEntity(Long id);

    List<Event> findByNameStartsWithIgnoreCase(String name);

    Event save(Event event);

    void delete(Long id);


    }
