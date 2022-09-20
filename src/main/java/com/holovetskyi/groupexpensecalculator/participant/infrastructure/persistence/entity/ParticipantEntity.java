package com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.holovetskyi.groupexpensecalculator.participant.domain.Participant;
import com.holovetskyi.groupexpensecalculator.participant.domain.Payment;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.UpdateParticipantDTO;
import com.holovetskyi.groupexpensecalculator.config.jpa.BaseEntity;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "participant")
@ToString(exclude = {"events", "payments"})
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class ParticipantEntity extends BaseEntity {

    private String firstName;

    private String lastName;

//    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "participants", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("participant")
    private Set<EventEntity> events = new HashSet<>();

    @OneToMany( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "participant_id")
    @Fetch(FetchMode.SUBSELECT)
    @Singular
    private List<PaymentEntity> payments= new ArrayList<>();

    @Builder
    public ParticipantEntity(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Participant toParticipant(){
        return Participant.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .payment(toPayment())
                .build();
    }

    public ParticipantEntity updateFields(UpdateParticipantDTO participantDTO) {

        if (participantDTO.firstName() != null) {
            System.out.println(participantDTO.firstName());
            this.firstName = participantDTO.firstName();
        }

        if (participantDTO.lastName() != null){
            this.lastName = participantDTO.lastName();
        }

        if (participantDTO.email() != null){
            this.email = participantDTO.email();
        }
        return this;
    }

    private List<Payment> toPayment() {
        return payments
                .stream()
                .map(PaymentEntity::toPayment)
                .toList();
    }

    public void addEvent(EventEntity event) {
        if (event == null){
            throw new IllegalArgumentException("Event not null");
        }

        this.events.add(event);
    }

    public void addPayment(Payment payment) {
        if (payment == null){
            throw new IllegalArgumentException("Participant not null");
        }
        payments.add(payment.toPaymentEntity());
    }
}
