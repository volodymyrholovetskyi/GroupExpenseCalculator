package com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.dao;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.payment.domain.Person;
import com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepositoryDAO extends JpaRepository<PersonEntity, Long> {

    Person save(Person event);

    List<PersonEntity> findAll();

    Optional<PersonEntity> findById(Long id);

    void deleteById(Long id);
}
