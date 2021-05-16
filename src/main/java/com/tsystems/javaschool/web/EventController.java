package com.tsystems.javaschool.web;

import com.tsystems.javaschool.model.Event;
import com.tsystems.javaschool.model.EventStatus;
import com.tsystems.javaschool.service.event.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.tsystems.javaschool.util.ControllerUtil.getId;
import static com.tsystems.javaschool.util.ValidationUtil.assureIdConsistent;

@Controller
@RequestMapping("/events")
public class EventController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        Event event = service.get(getId(request));
        model.addAttribute("event", event);
        return "eventForm";
    }

    @PostMapping
    public String update(HttpServletRequest request) {
        Event event = service.get(getId(request));
        event.setEventStatus(EventStatus.valueOf(request.getParameter("status")));
        event.setMessage(request.getParameter("message"));
        assureIdConsistent(event, getId(request));
        service.update(event);
        return "redirect:/events";
    }
}
