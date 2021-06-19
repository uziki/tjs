package com.tsystems.javaschool.util;

import com.tsystems.javaschool.model.Event;
import com.tsystems.javaschool.to.EventTo;

import java.util.ArrayList;
import java.util.List;

public class EventUtil {
    public static List<EventTo> getTos(List<Event> events) {
        List<EventTo> eventTos = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            eventTos.add(getTo(events.get(i)));
        }
        return eventTos;
    }

    public static EventTo getTo(Event event) {
        EventTo eventTo = new EventTo();
        eventTo.setTime(event.getDateTime().format(DateTimeUtil.TIME_FORMATTER));
        eventTo.setPatient(event.getPatient().getName());
        eventTo.setEventPrescription(event.getPrescription().getProcedureOrMedicine().getName() +
                (event.getDose() > 0 ? " " + event.getDose() + " pcs" : ""));
        return eventTo;
    }
}
