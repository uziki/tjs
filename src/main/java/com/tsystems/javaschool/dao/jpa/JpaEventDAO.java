package com.tsystems.javaschool.dao.jpa;

import com.tsystems.javaschool.dao.EventDAO;
import com.tsystems.javaschool.model.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.tsystems.javaschool.util.DateTimeUtil.DAYS_BEFORE_TOMORROW;

@Repository
public class JpaEventDAO implements EventDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Event save(Event event) {
        if (event.isNew()) {
            em.persist(event);
            return event;
        } else {
            return em.merge(event);
        }
    }

    @Override
    public List<Event> getByPrescriptionId(int prescriptionId) {
        return em.createNamedQuery(Event.GET_BY_PRESCRIPTION, Event.class)
                .setParameter("prescriptionId", prescriptionId)
                .getResultList();
    }

    @Override
    public Event get(int id) {
        return em.find(Event.class, id);
    }

    @Override
    public List<Event> getAll() {
        return em.createNamedQuery(Event.ALL_SORTED, Event.class).getResultList();
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Event.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Event> getBetweenDates(LocalDateTime startLdt, LocalDateTime endLdt) {
        return em.createNamedQuery(Event.GET_BETWEEN_DATE, Event.class)
                .setParameter("startDateTime", startLdt)
                .setParameter("endDateTime", endLdt)
                .getResultList();
    }

    @Override
    public List<Event> findByName(String name) {
        return em.createNamedQuery(Event.FIND_BY_NAME, Event.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Event> findByToday() {
        return em.createNamedQuery(Event.FIND_BY_TODAY, Event.class)
                .setParameter("tomorrowDate", LocalDate.now().plusDays(DAYS_BEFORE_TOMORROW).atStartOfDay())
                .getResultList();
    }
}
