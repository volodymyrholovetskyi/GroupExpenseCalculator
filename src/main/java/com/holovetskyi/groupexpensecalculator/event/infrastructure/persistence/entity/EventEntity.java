package com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.holovetskyi.groupexpensecalculator.event.domain.Currency;
import com.holovetskyi.groupexpensecalculator.event.domain.CurrentStatus;
import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.web.dto.UpdateEventDTO;
import com.holovetskyi.groupexpensecalculator.config.jpa.BaseEntity;
import com.holovetskyi.groupexpensecalculator.participant.domain.Participant;
import com.holovetskyi.groupexpensecalculator.participant.infrastructure.persistence.entity.ParticipantEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.holovetskyi.groupexpensecalculator.event.domain.Currency.*;
import static com.holovetskyi.groupexpensecalculator.event.domain.CurrentStatus.IN_PROGRESS;

@Entity
@Getter
@Setter
@Table(name = "event")
@ToString(exclude = "participants")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class EventEntity extends BaseEntity {

    private String name;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private CurrentStatus status;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "event_participant",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "participant_id")}
    )
    @JsonIgnoreProperties("events")
    private Set<ParticipantEntity> participants = new HashSet<>();
    @CreatedDate
    private LocalDateTime createAt;


    @Builder
    public EventEntity(String name, Currency currency, CurrentStatus status) {
        this.name = name;
        this.currency = Optional.ofNullable(currency).orElse(PLN);
        this.status = Optional.ofNullable(status).orElse(IN_PROGRESS);

    }

    public EventEntity updateFields(UpdateEventDTO eventDTO) {
        if (eventDTO.name() != null) {
            System.out.println(eventDTO.name());
            this.name = eventDTO.name();
        }

        if (eventDTO.currency() != null){
            this.currency = eventDTO.currency();
        }
        return this;
    }

    public Event toEvent() {
        return Event
                .builder()
                .id(id)
                .name(name)
                .currency(currency)
                .status(status)
                .participants(toParticipantSet())
                .createAt(createAt)
                .build();
    }

    public void addParticipant(Set<ParticipantEntity> participants) {
        if (participants == null){
            throw new IllegalArgumentException("Participant not null");
        }
        this.participants = participants;
    }

   Set<Participant> toParticipantSet(){
        return participants
                .stream()
                .map(ParticipantEntity::toParticipant)
                .collect(Collectors.toSet());
   }
}


