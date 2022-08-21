package com.holovetskyi.groupexpensecalculator.event.domain.repo;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    public List<Event> findAll();

    public Optional<Event> findById(Long id);

    List<Event> findByNameStartsWithIgnoreCase(String name);

    Event save(Event event);

}
