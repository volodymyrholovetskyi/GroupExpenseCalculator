package com.holovetskyi.groupexpensecalculator.customer.domain.repo;

import com.holovetskyi.groupexpensecalculator.customer.domain.Customer;
import com.holovetskyi.groupexpensecalculator.customer.infrastructure.persistence.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    Optional<CustomerEntity> findByIdPersonEntity(Long id);

    List<Customer> findByNameStartsWithIgnoreCase(String name);

    Customer save(Customer customer);

    void delete(Long id);
}
