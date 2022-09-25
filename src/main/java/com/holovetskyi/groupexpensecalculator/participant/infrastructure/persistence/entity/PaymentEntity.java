package com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity{

private BigDecimal payment;
}
