package com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.holovetskyi.groupexpensecalculator.event.domain.Currency;
import com.holovetskyi.groupexpensecalculator.event.domain.CurrentStatus;
import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.web.dto.UpdateEventDTO;
import com.holovetskyi.groupexpensecalculator.jpa.BaseEntity;
import com.holovetskyi.groupexpensecalculator.customer.domain.Customer;
import com.holovetskyi.groupexpensecalculator.customer.infrastructure.persistence.entity.CustomerEntity;
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
@ToString(exclude = "customers")
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
            name = "event_customer",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    @JsonIgnoreProperties("events")
    private Set<CustomerEntity> customers = new HashSet<>();
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
                .customers(toCustomerSet())
                .createAt(createAt)
                .build();
    }

    public void addCustomer(Set<CustomerEntity> customers) {
        if (customers == null){
            throw new IllegalArgumentException("Person not null");
        }
        this.customers = customers;
    }

   Set<Customer> toCustomerSet(){
        return customers
                .stream()
                .map(CustomerEntity::toCustomer)
                .collect(Collectors.toSet());
   }
}


