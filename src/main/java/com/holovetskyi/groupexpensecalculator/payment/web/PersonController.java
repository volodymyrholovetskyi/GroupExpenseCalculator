package com.holovetskyi.groupexpensecalculator.payment.web;

import com.holovetskyi.groupexpensecalculator.event.web.CreatedEntityURI;
import com.holovetskyi.groupexpensecalculator.payment.application.PersonService;
import com.holovetskyi.groupexpensecalculator.payment.web.dto.CreatePersonDTO;
import com.holovetskyi.groupexpensecalculator.payment.web.dto.GetPersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("persons")
@RequiredArgsConstructor
public class PersonController {
   private final PersonService service;

   @GetMapping
   @ResponseStatus(OK)
   List<GetPersonDTO> getAll(){
      return service.getAll();
   }

   @PostMapping
   ResponseEntity<Void> addPerson(@Valid @RequestBody CreatePersonDTO personDTO) {
      System.out.println(personDTO);
      CreatePersonDTO createPersonDTO = service.addPerson(personDTO);
      CreatedPersonURI uri = new CreatedPersonURI("/" + createPersonDTO.id().toString());
      return ResponseEntity.created(uri.createdPersonUri()).build();
   }
}
