package com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.holovetskyi.groupexpensecalculator.participant.domain.Participant;
import com.holovetskyi.groupexpensecalculator.participant.domain.value_object.Money;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.UpdateParticipantDTO;
import com.holovetskyi.groupexpensecalculator.config.jpa.BaseEntity;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "participant")
@ToString(exclude = {"events"})
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class ParticipantEntity extends BaseEntity {

    private String firstName;

    private String lastName;

//    @Column(unique = true)
    private String email;
    @Embedded
    private Money money;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "participants", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("participant")
    private Set<EventEntity> events = new HashSet<>();

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
                .money(money)
                .build();
    }

    public ParticipantEntity updateFields(UpdateParticipantDTO participantDTO) {

        if (participantDTO.firstName() != null) {
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


    public void addEvent(EventEntity event) {
        if (event == null){
            throw new IllegalArgumentException("Event not null");
        }

        this.events.add(event);
    }
}
