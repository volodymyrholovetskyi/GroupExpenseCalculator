package com.holovetskyi.groupexpensecalculator.payment.domain.repo;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.payment.domain.Person;
import com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.entity.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    List<Person> findAll();

    Optional<Person> findById(Long id);

    Optional<PersonEntity> findByIdPersonEntity(Long id);

    List<Person> findByNameStartsWithIgnoreCase(String name);

    Person save(Person event);

    void delete(Long id);
}
