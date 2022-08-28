package com.holovetskyi.groupexpensecalculator.customer.infrastructure.persistence.dao;

import com.holovetskyi.groupexpensecalculator.customer.domain.Customer;
import com.holovetskyi.groupexpensecalculator.customer.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryDAO extends JpaRepository<CustomerEntity, Long> {

    Customer save(Customer event);

    List<CustomerEntity> findAll();

    Optional<CustomerEntity> findById(Long id);

    void deleteById(Long id);
}
