package com.holovetskyi.groupexpensecalculator;

import com.holovetskyi.groupexpensecalculator.event.application.EventService;
import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao.impl.EventRepositoryImpl;
import com.holovetskyi.groupexpensecalculator.participant.domain.Participant;
import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.dao.impl.ParticipantRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Starter implements CommandLineRunner {


    private final EventService eventService;
    private final EventRepositoryImpl repository;
    private final ParticipantRepositoryImpl repositoryPerson;

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

        Participant vova = Participant
                .builder()
                .firstName("Volodymyr")
                .lastName("Holovetskyi")
                .email("volodymyr@gmai.com")
                .build();

        Participant mariia = Participant
                .builder()
                .firstName("Mariia")
                .lastName("Khort")
                .email("maria@gmai.com")
                .build();

        repository.save(warsaw);
        repository.save(coffee);
        repositoryPerson.save(vova);
        repositoryPerson.save(mariia);

    }
}
