package com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.holovetskyi.groupexpensecalculator.event.domain.CurrentStatus;
import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.jpa.BaseEntity;
import com.holovetskyi.groupexpensecalculator.person.Person;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import static com.holovetskyi.groupexpensecalculator.event.domain.CurrentStatus.IN_PROGRESS;
import static java.util.Collections.*;

@Entity
@Getter
@Setter
@Table(name = "event")
@ToString(exclude = "persons")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class EventEntity extends BaseEntity {
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "event_person",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "person_id") }
    )
    private Set<Person> persons = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private CurrentStatus status;

    @CreatedDate
    private LocalDateTime createAt;

    @Builder
    public EventEntity(String name) {
        this.name = name;
        this.status = Optional.ofNullable(status).orElse(IN_PROGRESS);

    }

    public Event toEvent() {
        return Event
                .builder()
                .id(id)
                .name(name)
                .persons(persons)
                .build();
    }
}


