package com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.dao.impl;

import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import com.holovetskyi.groupexpensecalculator.payment.domain.Person;
import com.holovetskyi.groupexpensecalculator.payment.domain.repo.PersonRepository;
import com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.dao.PersonRepositoryDAO;
import com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.entity.PersonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonRepositoryDAO repositoryDAO;

    @Override
    public List<Person> findAll() {
        return repositoryDAO.findAll()
                .stream()
                .map(PersonEntity::toPerson)
                .toList();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.empty();
    }

    public Optional<PersonEntity> findByIdPersonEntity(Long id) {
        return repositoryDAO.findById(id);
    }

    @Override
    public List<Person> findByNameStartsWithIgnoreCase(String name) {
        return null;
    }

    @Override
    public Person save(Person newPerson) {
        PersonEntity person = repositoryDAO.save(newPerson.toPersonEntity());
        return person.toPerson();
    }

    @Override
    public void delete(Long id) {

    }
}
