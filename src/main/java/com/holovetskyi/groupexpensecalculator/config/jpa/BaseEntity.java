package com.holovetskyi.groupexpensecalculator.config.jpa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    protected Long id;

    protected String uuid = UUID.randomUUID().toString();

    @Version
    protected long version;

}
