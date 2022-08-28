package com.holovetskyi.groupexpensecalculator.customer.infrastructure.persistence.entity;

import com.holovetskyi.groupexpensecalculator.jpa.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@ToString
@Table(name = "payment")
@EntityListeners({AuditingEntityListener.class})
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity extends BaseEntity {

    private String namePayment;

    private BigDecimal payment;

    @ManyToOne
    private CustomerEntity customer;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;
}
