package com.tsystems.javaschool.web;

import com.tsystems.javaschool.model.Event;
import com.tsystems.javaschool.model.EventStatus;
import com.tsystems.javaschool.service.event.EventService;
import com.tsystems.javaschool.util.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static com.tsystems.javaschool.util.ControllerUtil.getId;
import static com.tsystems.javaschool.web.SecurityUtil.setAuthUserName;

@Controller
@RequestMapping("/events")
public class EventController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private EventService service;
    private Sort sort = Sort.ALL;
    private String findName = "";

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping("/done")
    public String done(HttpServletRequest request) {
        Event event = service.get(getId(request));
        log.info("done event {}", event);
        event.setEventStatus(EventStatus.STATUS_DONE);
        service.update(event);
        return "redirect:/events";
    }

    @GetMapping("/cancel")
    public String update(HttpServletRequest request, Model model) {
        Event event = service.get(getId(request));
        model.addAttribute("event", event);
        setAuthUserName(model);
        return "eventForm";
    }

    @PostMapping
    public String cancel(HttpServletRequest request) {
        Event event = service.get(getId(request));
        log.info("cancel event {}", event);
        event.setEventStatus(EventStatus.STATUS_CANCELED);
        event.setMessage(request.getParameter("message"));
        service.update(event);
        return "redirect:/events";
    }

    @GetMapping("/sort")
    public String sort(HttpServletRequest request) {
        sort = Sort.valueOf(request.getParameter("sort").toUpperCase());
        return "redirect:/events";
    }


    @GetMapping
    public String get(Model model) {
        switch (sort) {
            case ALL:
                model.addAttribute("events", service.getAll());
                break;
            case THIS_DAY:
                model.addAttribute("events", service.getBetweenDates(LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
                break;
            case THIS_HOUR:
                model.addAttribute("events", service.getBetweenDates(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
                break;
            case BY_NAME:
                model.addAttribute("events", service.findByName(findName));
                model.addAttribute("find", findName);
        }
        model.addAttribute("sort", sort);
        setAuthUserName(model);
        return "events";
    }

    @PostMapping("/find")
    public String find(HttpServletRequest request) {
        sort = Sort.BY_NAME;
        findName = request.getParameter("find");
        return "redirect:/events";
    }

}
