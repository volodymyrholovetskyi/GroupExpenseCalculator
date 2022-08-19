package com.holovetskyi.groupexpensecalculator;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Starter implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {
        init();
    }

    private void init() {
        Arrays.asList(
                Event.builder()
                        .id(1L)
                        .name("A trip to Warsaw")
                        .build(),

                Event.builder()
                        .id(1L)
                        .name("A trip to Warsaw")
                        .build())
                .forEach(System.out::println);
    }
}
