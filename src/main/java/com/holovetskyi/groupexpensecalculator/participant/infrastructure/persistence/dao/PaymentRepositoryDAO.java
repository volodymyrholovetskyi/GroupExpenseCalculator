package com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.dao;

import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepositoryDAO extends JpaRepository<PaymentEntity, Long> {


}
