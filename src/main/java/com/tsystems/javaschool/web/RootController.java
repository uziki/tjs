package com.tsystems.javaschool.web;

import com.tsystems.javaschool.service.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    private PatientService service;

    @Autowired
    public void setService(PatientService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/patients")
    public String getPatients(Model model) {
        model.addAttribute("patients", service.getAll());
        return "patients";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
