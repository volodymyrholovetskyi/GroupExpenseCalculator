package com.holovetskyi.groupexpensecalculator.event.domain.repo;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    public Optional<Event> save();

    public List<Event> findAll();

    public Optional<Event> findById(Long id);

}
