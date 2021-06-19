package com.tsystems.javaschool.service.event;

import com.tsystems.javaschool.dao.EventDAO;
import com.tsystems.javaschool.model.Event;
import com.tsystems.javaschool.to.EventTo;
import com.tsystems.javaschool.util.EventUtil;
import com.tsystems.javaschool.util.MQSender;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
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
    public List<Event> getByPrescriptionId(int prescriptionId) {
        return dao.getByPrescriptionId(prescriptionId);
    }

    @Override
    public void update(Event event) throws NotFoundException {
        notifyMQ();
        checkNotFoundWithId(dao.save(event), event.getId());
    }

    @Override
    public Event create(Event event) {
        return dao.save(event);
    }

    @Override
    public List<Event> getBetweenDates(LocalDateTime startLdt, LocalDateTime endLdt) {
        List<Event> events;
        try {
            events = dao.getBetweenDates(startLdt, endLdt);
        } catch (NoResultException e) {
            return null;
        }
        return events;
    }

    @Override
    public List<Event> findByName(String name) {
        List<Event> events;
        try {
            events = dao.findByName("%" + name + "%");
        } catch (NoResultException e) {
            return null;
        }
        return events;
    }

    @Override
    public List<EventTo> findByToday() {
        return EventUtil.getTos(dao.findByToday());
    }

    @Override
    public void notifyMQ() {
        MQSender.sendMessage();
    }
}
