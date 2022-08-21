package com.holovetskyi.groupexpensecalculator;

import com.holovetskyi.groupexpensecalculator.event.application.EventService;
import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.impl.EventRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Starter implements CommandLineRunner {


    private final EventService eventService;
    private final EventRepositoryImpl repository;

    @Override
    public void run(String... args) throws Exception {
        init();
    }

    private void init() {

        Event warsaw = Event.builder()
                .name("A trip to Warsaw")
                .build();

        Event coffee = Event.builder()
                .name("Coffee")
                .build();

        repository.save(warsaw);
        repository.save(coffee);
    }
}
