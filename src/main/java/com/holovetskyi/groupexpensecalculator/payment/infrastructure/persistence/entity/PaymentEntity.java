package com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.entity;

import com.holovetskyi.groupexpensecalculator.jpa.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@ToString
@EntityListeners({AuditingEntityListener.class})
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity extends BaseEntity {

    private String namePayment;

    private BigDecimal payment;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;
}
