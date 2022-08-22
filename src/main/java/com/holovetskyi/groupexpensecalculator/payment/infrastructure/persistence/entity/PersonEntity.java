package com.holovetskyi.groupexpensecalculator.payment.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.holovetskyi.groupexpensecalculator.jpa.BaseEntity;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = {"events", "payments"})
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class PersonEntity extends BaseEntity {

    private String firstName;

    private String lastName;

//    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "persons", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("persons")
    private Set<EventEntity> events = new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @Singular
    private List<PaymentEntity> paymentEntities = new ArrayList<>();

    @Builder
    public PersonEntity(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
