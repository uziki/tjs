package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.Event;

import java.util.List;

public interface EventDAO {
    Event save(Event event);

    boolean delete(int id);

    Event get(int id);

    List<Event> getAll();

    List<Event> getByPrescriptionId(int prescriptionId);
}
