package com.tsystems.javaschool.service.event;

import com.tsystems.javaschool.model.Event;
import com.tsystems.javaschool.util.exception.NotFoundException;

import java.util.List;

public interface EventService {
    Event get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Event> getAll();

    void update(Event event) throws NotFoundException;

    Event create(Event event);

    List<Event> getByPrescriptionId(int prescriptionId);
}
