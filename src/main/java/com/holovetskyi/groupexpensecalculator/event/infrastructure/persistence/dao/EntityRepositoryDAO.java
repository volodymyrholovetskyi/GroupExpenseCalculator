package com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.dao;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import com.holovetskyi.groupexpensecalculator.event.infrastructure.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntityRepositoryDAO extends JpaRepository<EventEntity, Long> {

//    List<EventEntity> findByNameStartsWithIgnoreCase(String name);

//    @Query("SELECT e FROM EventEntity e JOIN e.persons p " +
//            "WHERE lower(e.name) LIKE lower(concat('%', :name, '%')) ")
//    List<EventEntity> findByName(@Param("name") String name);

    EventEntity save(EventEntity event);

    List<EventEntity> findAll();

    Optional<EventEntity> findById(Long id);

    void deleteById(Long id);


    List<EventEntity> findByNameStartsWithIgnoreCase(String name);
}
