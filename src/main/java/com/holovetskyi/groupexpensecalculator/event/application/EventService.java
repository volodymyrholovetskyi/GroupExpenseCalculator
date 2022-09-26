package com.holovetskyi.groupexpensecalculator.event.application;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.domain.repo.EventRepository;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.EntityRepositoryDAO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.CreateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.GetEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.CustomerIdsDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.UpdateEventDTO;
import com.holovetskyi.groupexpensecalculator.event.web.response.UpdateEventResponse;
import com.holovetskyi.groupexpensecalculator.participant.domain.repo.ParticipantRepository;
import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity.ParticipantEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final EntityRepositoryDAO repositoryDAO;
    private final ParticipantRepository participantRepository;

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
//                .findByIdEventEntity(id)
//                .map(event -> {
//                    event.updateFields(eventDTO);
//                    return UpdateEventResponse.SUCCESS;
//                })
//                .orElseGet(() -> new UpdateEventResponse(false,
//                        Collections.singletonList("Product not found with id: " + id)));
//    }

    @Transactional
    public UpdateEventResponse updateEvent(Long id, UpdateEventDTO eventDTO) {
        return repositoryDAO
                .findById(id)
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
    public UpdateEventResponse updateEventWithPerson(Long id, CustomerIdsDTO idsDTO) {
        return repository
                .findByIdEventEntity(id)
                .map(event -> {
                    Set<ParticipantEntity> personEntities = fetchCustomerEntityByIds(idsDTO.customerIds());
                    event.addParticipant(personEntities);
                    return UpdateEventResponse.SUCCESS;
                })
                .orElseGet(() -> new UpdateEventResponse(false,
                        Collections.singletonList("Event not found with id: " + id)));
    }

    private Set<ParticipantEntity> fetchCustomerEntityByIds(Set<Long> customers) {
        return customers.stream()
                .map(customerId -> participantRepository
                        .findByIdParticipantEntity(customerId)
                        .orElseThrow(() -> new IllegalArgumentException("Unable to find customer with id: " + customerId))
                ).collect(Collectors.toSet());
    }
}


