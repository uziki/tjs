package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventDAO {
    Event save(Event event);

    boolean delete(int id);

    Event get(int id);

    List<Event> getAll();

    List<Event> getByPrescriptionId(int prescriptionId);

    List<Event> getBetweenDates(LocalDateTime startLdt, LocalDateTime endLdt);

    List<Event> findByName(String name);
}
