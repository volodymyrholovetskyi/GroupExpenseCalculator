package com.holovetskyi.groupexpensecalculator.customer.infrastructure.persistence.dao.impl;

import com.holovetskyi.groupexpensecalculator.customer.domain.Customer;
import com.holovetskyi.groupexpensecalculator.customer.domain.repo.CustomerRepository;
import com.holovetskyi.groupexpensecalculator.customer.infrastructure.persistence.dao.CustomerRepositoryDAO;
import com.holovetskyi.groupexpensecalculator.customer.infrastructure.persistence.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerRepositoryDAO repositoryDAO;

    @Override
    public List<Customer> findAll() {
        return repositoryDAO.findAll()
                .stream()
                .map(CustomerEntity::toCustomer)
                .toList();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.empty();
    }

    public Optional<CustomerEntity> findByIdPersonEntity(Long id) {
        return repositoryDAO.findById(id);
    }

    @Override
    public List<Customer> findByNameStartsWithIgnoreCase(String name) {
        return null;
    }

    @Override
    public Customer save(Customer newCustomer) {
        CustomerEntity person = repositoryDAO.save(newCustomer.toCustomerEntity());
        return person.toCustomer();
    }

    @Override
    public void delete(Long id) {

    }
}
