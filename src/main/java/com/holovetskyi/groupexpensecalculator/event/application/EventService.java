package com.holovetskyi.groupexpensecalculator.event.application;

import com.holovetskyi.groupexpensecalculator.event.db.EventJpaRepository;
import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService {

        private final EventJpaRepository repository;

        public List<Event> getAll() {
            return repository.findAll();
        }

        public List<Event> findByName(String name) {
            return repository.findByNameStartsWithIgnoreCase(name);
        }

        public Optional<Event> findById(Long id) {
            return repository.findById(id);
        }
}
