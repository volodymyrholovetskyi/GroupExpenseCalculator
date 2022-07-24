package com.holovetskyi.groupexpensecalculator.event.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.holovetskyi.groupexpensecalculator.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;

import static com.holovetskyi.groupexpensecalculator.event.domain.CurrentStatus.*;

@Entity
@Table(name = "event")
@Getter
@Setter
@ToString(exclude = "payers")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class MeetingEvent extends BaseEntity {

    private String name;

    @ManyToMany
    @JsonIgnoreProperties("event")
    private List<Person> persons = new ArrayList<>();

    @Builder.Default
    private CurrentStatus status = IN_PROGRESS;




}
