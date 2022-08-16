package com.holovetskyi.groupexpensecalculator.event.application;

import com.holovetskyi.groupexpensecalculator.event.domain.repo.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository repository;

//    public List<EventEntity> getAll() {
//        return repository.findAll();
//    }
//
//    public List<EventEntity> findByName(String name) {
//        return repository.findByNameStartsWithIgnoreCase(name);
//    }
//
//    public Optional<EventEntity> findById(Long id) {
//        return repository.findById(id);
}
}
