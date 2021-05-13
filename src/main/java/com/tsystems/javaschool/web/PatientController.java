package com.tsystems.javaschool.web;


import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.service.patient.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private PatientService service;

    @Autowired
    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        int userId = SecurityUtil.authUserId();
        int patientId = getId(request);
        log.info("delete patient {} for user {}", patientId, userId);
        service.delete(patientId);
        return "redirect:/patients";
    }



    /*@GetMapping("/events")
    public String getEvents(Model model) {
        model.addAttribute("events", dao.getAll());
        return "events";
    }*/

    /*@PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:meals";*/

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
