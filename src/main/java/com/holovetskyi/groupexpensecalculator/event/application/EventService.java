package com.holovetskyi.groupexpensecalculator.event.application;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.domain.repo.EventRepository;
import com.holovetskyi.groupexpensecalculator.event.web.dto.CreateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.GetEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.PersonIdsDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.UpdateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.response.UpdateEventResponse;
import com.holovetskyi.groupexpensecalculator.customer.domain.repo.CustomerRepository;
import com.holovetskyi.groupexpensecalculator.customer.infrastructure.persistence.entity.CustomerEntity;
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
    private final CustomerRepository customerRepository;

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
    public UpdateEventResponse updateEventWithPerson(Long id, PersonIdsDTO idsDTO) {
        return repository
                .findByIdEventEntity(id)
                .map(event -> {
                    Set<CustomerEntity> personEntities = fetchPersonEntityByIds(idsDTO.ids());
                    event.addCustomer(personEntities);
                    return UpdateEventResponse.SUCCESS;
                })
                .orElseGet(() -> new UpdateEventResponse(false,
                        Collections.singletonList("Event not found with id: " + id)));
    }

    private Set<CustomerEntity> fetchPersonEntityByIds(Set<Long> persons) {
        return persons.stream()
                .map(personId -> customerRepository
                        .findByIdPersonEntity(personId)
                        .orElseThrow(() -> new IllegalArgumentException("Unable to find author with id: " + personId))
                ).collect(Collectors.toSet());
    }
}


