package com.holovetskyi.groupexpensecalculator.person;

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
public class Person extends BaseEntity {

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "persons", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("persons")
    private Set<EventEntity> eventEntities = new HashSet<>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "payerId")
    @Singular
    private List<Payment> payments = new ArrayList<>();

    @Builder
    public Person (String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
