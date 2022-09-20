package com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.dao.impl;

import com.holovetskyi.groupexpensecalculator.participant.domain.Payment;
import com.holovetskyi.groupexpensecalculator.participant.domain.repo.PaymentRepository;
import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.dao.PaymentRepositoryDAO;
import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentRepositoryDAO repositoryDAO;

    @Override
    public List<Payment> findAll() {
        List<PaymentEntity> paymentEntity = repositoryDAO.findAll();
        return paymentEntity
                .stream()
                .map(PaymentEntity::toPayment)
                .toList();
    }

    @Override
    public Payment save(Payment payment) {
       PaymentEntity paymentEntity = payment.toPaymentEntity();
        return repositoryDAO.save(paymentEntity).toPayment();
    }

    @Override
    public void delete(Long id) {
        repositoryDAO.deleteById(id);
    }

    @Override
    public Optional<PaymentEntity> findByIdPaymentEntity(Long id) {
        return repositoryDAO.findById(id);
    }

    @Override
    public Optional<Payment> findById(Long id){
        return repositoryDAO.findById(id).map(PaymentEntity::toPayment);
    }
}
