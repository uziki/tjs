package com.tsystems.javaschool.dao.jpa;

import com.tsystems.javaschool.dao.EventDAO;
import com.tsystems.javaschool.model.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
}
