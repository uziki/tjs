package com.tsystems.javaschool.service.event;

import com.tsystems.javaschool.dao.EventDAO;
import com.tsystems.javaschool.model.Event;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private EventDAO dao;

    @Autowired
    public EventServiceImpl(EventDAO dao) {
        this.dao = dao;
    }

    @Override
    public Event get(int id) throws NotFoundException {
        return dao.get(id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        dao.delete(id);
    }

    @Override
    public List<Event> getAll() {
        return dao.getAll();
    }

    @Override
    public void update(Event event) throws NotFoundException {
        dao.save(event);
    }

    @Override
    public Event create(Event event) {
        return dao.save(event);
    }
}
