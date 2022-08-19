package com.holovetskyi.groupexpensecalculator.event.application;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.domain.repo.EventRepository;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.impl.EventRepositoryImpl;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.event.web.dto.CreateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.GetEventDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepositoryImpl repository;

    public List<Event> getAll() {
        return repository.findAll();
    }

    public CreateEventDTO addEvent(CreateEventDTO eventDto) {
        Event event = repository.save(eventDto.toEvent());
        return event.toCreateEventDTO();
    }

    public List<Event> findByName(String name) {
        return repository.findByNameStartsWithIgnoreCase(name);
    }

    public Optional<GetEventDTO> findById(Long id) {
        return repository.findById(id).map(Event::toGetEventDTO);
}
}
