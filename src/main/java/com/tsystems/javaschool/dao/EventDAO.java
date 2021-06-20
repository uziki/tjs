package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.Event;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Patient DAO interface.
 */
public interface EventDAO {

    /**
     * Creates or updates Event.
     *
     * @param event need to be saved
     * @return saved Event
     */
    Event save(Event event);

    /**
     * Deletes Event.
     *
     * @param id need to be deleted
     * @return {@code true} if successful
     */
    boolean delete(int id);

    /**
     * Gets Event by id.
     *
     * @param id of Event
     * @return Event by id
     */
    Event get(int id);

    /**
     * Gets all Events.
     *
     * @return all Events
     */
    List<Event> getAll();

    /**
     * Gets Events by prescription id.
     *
     * @param prescriptionId of prescription
     * @return all Events by prescription id
     */
    List<Event> getByPrescriptionId(int prescriptionId);

    /**
     * Gets all Events between two dates.
     *
     * @param startLdt start date
     * @param endLdt   end date
     * @return all Events between two dates
     */
    List<Event> getBetweenDates(LocalDateTime startLdt, LocalDateTime endLdt);

    /**
     * Gets all Events by patient name.
     *
     * @param name of patient
     * @return all Events by patient name
     */
    List<Event> findByName(String name);

    /**
     * Gets all Events for today.
     *
     * @return all Events for today
     */
    List<Event> findByToday();
}
