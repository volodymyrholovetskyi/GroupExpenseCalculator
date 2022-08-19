package com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity;

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

@Entity
@Getter
@Setter
@Table(name = "event")
@ToString(exclude = "persons")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class EventEntity extends BaseEntity {
    private String nameEvent;

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
        this.nameEvent = name;
        this.status = Optional.ofNullable(status).orElse(IN_PROGRESS);

    }

    public Event toEvent() {
        return Event.builder()
                .id(id)
                .name(nameEvent)
                .build();
    }
}


