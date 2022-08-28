package com.holovetskyi.groupexpensecalculator.customer.web;

import com.holovetskyi.groupexpensecalculator.customer.application.CustomerService;
import com.holovetskyi.groupexpensecalculator.customer.web.dto.CreateCustomerDTO;
import com.holovetskyi.groupexpensecalculator.customer.web.dto.GetCustomerDTO;
import com.holovetskyi.groupexpensecalculator.event.web.dto.PersonIdsDTO;
import com.holovetskyi.groupexpensecalculator.event.web.response.UpdateEventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {
   private final CustomerService service;

   @GetMapping
   @ResponseStatus(OK)
   List<GetCustomerDTO> getAll(){
      return service.getAll();
   }

   @PostMapping
   ResponseEntity<Void> addPerson(@Valid @RequestBody CreateCustomerDTO personDTO) {
      System.out.println(personDTO);
      CreateCustomerDTO createCustomerDTO = service.addCustomer(personDTO);
      CreatedCustomerURI uri = new CreatedCustomerURI("/" + createCustomerDTO.id().toString());
      return ResponseEntity.created(uri.createdPersonUri()).build();
   }

   @PutMapping(value = "/{id}")
   @ResponseStatus(ACCEPTED)
   public void addPaymentFromCustomer(@PathVariable Long id, @RequestBody CreatePaymentDTO createPaymentDTO) {
      UpdateEventResponse response = service.addPaymentFromCustomer(id, createPaymentDTO);

      if (!response.success()) {
         String message = String.join(", ", response.errors());
         throw new ResponseStatusException(BAD_REQUEST, message);
      }
   }
}
