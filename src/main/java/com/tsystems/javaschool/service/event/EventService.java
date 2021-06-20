package com.tsystems.javaschool.service.event;

import com.tsystems.javaschool.model.Event;
import com.tsystems.javaschool.to.EventTo;
import com.tsystems.javaschool.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Event service interface.
 */
public interface EventService {

    /**
     * Gets Event.
     *
     * @param id of Event
     * @return Event
     * @throws NotFoundException if not found
     */
    Event get(int id) throws NotFoundException;

    /**
     * Deletes Event.
     *
     * @param id of Event
     * @throws NotFoundException if not found
     */
    void delete(int id) throws NotFoundException;

    /**
     * Gets all Events.
     *
     * @return all Events
     */
    List<Event> getAll();

    /**
     * Updates Event.
     *
     * @param event need to be updated
     * @throws NotFoundException
     */
    void update(Event event) throws NotFoundException;

    /**
     * Creates Event.
     *
     * @param event need to be saved
     * @return saved Event
     */
    Event create(Event event);

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
    List<EventTo> findByToday();

    /**
     * Send message to MQ.
     */
    void notifyMQ();
}
