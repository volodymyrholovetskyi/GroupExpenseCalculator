package com.holovetskyi.groupexpensecalculator.event.application;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.domain.repo.EventRepository;
import com.holovetskyi.groupexpensecalculator.event.web.dto.CreateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.GetEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.ResourceIdsDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.UpdateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.response.UpdateEventResponse;
import com.holovetskyi.groupexpensecalculator.payment.domain.repo.PersonRepository;
import com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.entity.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final PersonRepository personRepository;

    public List<GetEventDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(Event::toGetEventDTO)
                .toList();
    }

    public Optional<GetEventDTO> findById(Long id) {
        return repository.findById(id).map(Event::toGetEventDTO);
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

//    @Transactional
//    public UpdateEventResponse updateEvent(Long id, UpdateEventDTO eventDTO) {
//        return repository
//                .findByIdEntity(id)
//                .map(event -> {
//                   event.updateFields(eventDTO);
//                    return UpdateEventResponse.SUCCESS;
//                })
//                .orElseGet(() -> new UpdateEventResponse(false,
//                        Collections.singletonList("Product not found with id: " + id)));
//    }

    @Transactional
    public UpdateEventResponse updateEvent(Long id, UpdateEventDTO eventDTO) {
        return repository
                .findByIdEventEntity(id)
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

    @Transactional
    public UpdateEventResponse updateEventPerson(Long id, ResourceIdsDTO idsDTO) {
        return repository
                .findByIdEventEntity(id)
                .map(event -> {
                    Set<PersonEntity> personEntities = fetchPersonEntityByIds(idsDTO.ids());
                    event.addPerson(personEntities);
                    return UpdateEventResponse.SUCCESS;
                })
                .orElseGet(() -> new UpdateEventResponse(false,
                        Collections.singletonList("Event not found with id: " + id)));
    }

    private Optional<Event> findByIdEvent(Long id) {
        return repository.findById(id);
    }

    private Set<PersonEntity> fetchPersonEntityByIds(Set<Long> persons) {
        return persons.stream()
                .map(personId -> personRepository
                        .findByIdPersonEntity(personId)
                        .orElseThrow(() -> new IllegalArgumentException("Unable to find author with id: " + personId))
                ).collect(Collectors.toSet());
    }
}


