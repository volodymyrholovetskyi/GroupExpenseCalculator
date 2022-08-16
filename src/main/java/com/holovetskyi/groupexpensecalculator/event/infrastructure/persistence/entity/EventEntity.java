package com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity;

import com.holovetskyi.groupexpensecalculator.event.domain.CurrentStatus;
import com.holovetskyi.groupexpensecalculator.jpa.BaseEntity;
import com.holovetskyi.groupexpensecalculator.person.Person;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import static com.holovetskyi.groupexpensecalculator.event.domain.CurrentStatus.*;

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
}


