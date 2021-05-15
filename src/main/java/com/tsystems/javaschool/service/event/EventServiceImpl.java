package com.tsystems.javaschool.service.event;

import com.tsystems.javaschool.dao.EventDAO;
import com.tsystems.javaschool.model.Event;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tsystems.javaschool.util.ValidationUtil.checkNotFoundWithId;

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
        return checkNotFoundWithId(dao.get(id), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dao.delete(id), id);
    }

    @Override
    public List<Event> getAll() {
        return dao.getAll();
    }

    @Override
    public void update(Event event) throws NotFoundException {
        checkNotFoundWithId(dao.save(event), event.getId());
    }

    @Override
    public Event create(Event event) {
        return dao.save(event);
    }
}
