package com.tsystems.javaschool.web;

import com.tsystems.javaschool.service.event.EventService;
import com.tsystems.javaschool.to.EventTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping(value = TodayEventsController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodayEventsController {
    static final String REST_URL = "/events/today";
    private EventService service;

    @Autowired
    public TodayEventsController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventTo> getTodayEvents () {
        return service.findByToday();
    }
}
