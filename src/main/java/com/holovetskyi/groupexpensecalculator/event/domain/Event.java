package com.holovetskyi.groupexpensecalculator.event.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.holovetskyi.groupexpensecalculator.BaseEntity;
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
public class Event extends BaseEntity {

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("events")
    private Set<Person> persons = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private CurrentStatus status;

    @CreatedDate
    private LocalDateTime createAt;

    @Builder
    public Event (String name) {
        this.name = name;
        this.status = Optional.ofNullable(status).orElse(IN_PROGRESS);

    }



}


