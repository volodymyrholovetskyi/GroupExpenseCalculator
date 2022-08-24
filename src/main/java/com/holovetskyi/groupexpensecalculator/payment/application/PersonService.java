package com.holovetskyi.groupexpensecalculator.payment.application;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.payment.domain.Person;
import com.holovetskyi.groupexpensecalculator.payment.domain.repo.PersonRepository;
import com.holovetskyi.groupexpensecalculator.payment.web.dto.CreatePersonDTO;
import com.holovetskyi.groupexpensecalculator.payment.web.dto.GetPersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public List<GetPersonDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(Person::toGetPersonDTO)
                .toList();
    }

    public CreatePersonDTO addPerson(CreatePersonDTO personDTO) {
        Person person = repository.save(personDTO.toPerson());
        return person.toCreatePersonDTO();
    }
}
