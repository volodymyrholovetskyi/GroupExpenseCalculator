package com.holovetskyi.groupexpensecalculator.event.application;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.domain.repo.EventRepository;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository repository;

    public List<Event> getAll() {
        return repository.findAll();
    }

    public CreateEventDto addEvent(CreateEventDto eventDto) {
        return repository.save(eventDto.toEvent());
    }

    public List<Event> findByName(String name) {
        return repository.findByNameStartsWithIgnoreCase(name);
    }

//    public Optional<EventEntity> findById(Long id) {
//        return repository.findById(id);
//}
}
