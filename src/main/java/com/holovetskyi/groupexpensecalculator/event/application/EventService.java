package com.holovetskyi.groupexpensecalculator.event.application;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.impl.EventRepositoryImpl;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.event.web.UpdateEventResponse;
import com.holovetskyi.groupexpensecalculator.event.web.dto.CreateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.GetEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.UpdateEventDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepositoryImpl repository;

    public List<GetEventDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(Event::toGetEventDTO)
                .toList();
    }

    public CreateEventDTO addEvent(CreateEventDTO eventDto) {
        Event event = repository.save(eventDto.toEvent());
        return event.toCreateEventDTO();
    }

    public List<GetEventDTO> findByName(String name) {
        return repository.findByNameStartsWithIgnoreCase(name)
                .stream()
                .map(Event::toGetEventDTO)
                .toList();
    }

    public Optional<GetEventDTO> findById(Long id) {
        return repository.findById(id).map(Event::toGetEventDTO);
    }

    @Transactional
    public UpdateEventResponse updateEvent(Long id, UpdateEventDTO eventDTO) {
        return repository
                .findByIdEntity(id)
                .map(event -> {
                   event.updateFields(eventDTO);
                    return UpdateEventResponse.SUCCESS;
                })
                .orElseGet(() -> new UpdateEventResponse(false,
                        Collections.singletonList("Product not found with id: " + id)));
    }

    public void deleteById(Long id) {
        repository.delete(id);
    }
}


