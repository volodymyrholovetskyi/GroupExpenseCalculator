package com.holovetskyi.groupexpensecalculator.participant.domain.repo;

import com.holovetskyi.groupexpensecalculator.participant.domain.Payment;
import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity.PaymentEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

    List<Payment> findAll();

    Payment save(Payment payment);

    void delete(Long id);

    Optional<PaymentEntity> findByIdPaymentEntity(Long id);

    Optional<Payment> findById(Long id);
}
