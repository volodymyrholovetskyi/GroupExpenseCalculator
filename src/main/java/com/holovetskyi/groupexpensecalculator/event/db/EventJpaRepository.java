package com.holovetskyi.groupexpensecalculator.event.db;

import com.holovetskyi.groupexpensecalculator.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventJpaRepository extends JpaRepository<Event, Long> {

    Event save(Event event);

    Optional<Event> findById(Long id);

    List<Event> findByNameStartsWithIgnoreCase(String name);

    @Query("SELECT e FROM Event e JOIN e.persons p " +
            "WHERE lower(e.name) LIKE lower(concat('%', :name, '%')) ")
    List<Event> findByName(@Param("name") String name);

}
