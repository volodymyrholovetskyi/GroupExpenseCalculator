package com.holovetskyi.groupexpensecalculator.customer.application;

import com.holovetskyi.groupexpensecalculator.customer.domain.Customer;
import com.holovetskyi.groupexpensecalculator.customer.domain.repo.CustomerRepository;
import com.holovetskyi.groupexpensecalculator.customer.web.dto.CreateCustomerDTO;
import com.holovetskyi.groupexpensecalculator.customer.web.dto.GetCustomerDTO;
import com.holovetskyi.groupexpensecalculator.event.web.response.UpdateEventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public List<GetCustomerDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(Customer::toGetCustomerDTO)
                .toList();
    }

    public CreateCustomerDTO addCustomer(CreateCustomerDTO customerDTO) {
        Customer customer = repository.save(customerDTO.toCustomer());
        return customer.toCreateCustomerDTO();
    }

    public UpdateEventResponse addPaymentFromCustomer(Long id, CreatePaymentDTO createPaymentDTO) {
        return null;
    }
}
