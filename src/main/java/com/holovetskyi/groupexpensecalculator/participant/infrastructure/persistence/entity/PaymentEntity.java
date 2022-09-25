package com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity;

import com.holovetskyi.groupexpensecalculator.participant.domain.Payment;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.UpdatePaymentDTO;
import com.holovetskyi.groupexpensecalculator.config.jpa.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
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

    private String name;

    private BigDecimal payment;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public Payment toPayment() {
        return Payment
                .builder()
                .name(name)
                .payment(payment)
                .createAt(createAt)
                .updateAt(updateAt)
                .build();
    }

    public PaymentEntity updateFields(UpdatePaymentDTO paymentDTO) {

        if (paymentDTO.name() != null) {
            this.name = paymentDTO.name();
        }

        if (paymentDTO.payment() != null) {
            this.payment = paymentDTO.payment();
        }
        return this;
    }
}
