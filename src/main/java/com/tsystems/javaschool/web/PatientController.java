package com.tsystems.javaschool.web;


import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.service.PrescriptionEventFacade;
import com.tsystems.javaschool.service.patient.PatientService;
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
import static com.tsystems.javaschool.util.ValidationUtil.checkNew;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private PatientService service;
    private PrescriptionEventFacade facadeService;

    @Autowired
    public PatientController(PatientService service, PrescriptionEventFacade facadeService) {
        this.service = service;
        this.facadeService = facadeService;
    }

    /*@GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        int doctorId = SecurityUtil.authUserId();
        int patientId = getId(request);
        log.info("delete patient {} for user {}", patientId, doctorId);
        service.delete(patientId);
        return "redirect:/patients";
    }*/

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        int doctorId = SecurityUtil.authUserId();
        int patientId = getId(request);
        log.info("delete patient {} for user {}", patientId, doctorId);
        facadeService.deletePatient(patientId);
        return "redirect:/patients";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("patient", new Patient());
        return "patientForm";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        int doctorId = SecurityUtil.authUserId();
        Patient patient = service.get(getId(request));
        model.addAttribute("patient", patient);
        return "patientForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        int doctorId = SecurityUtil.authUserId();
        Patient patient = new Patient(request.getParameter("name"),
                request.getParameter("diagnosis"),
                request.getParameter("insurance"),
                Boolean.parseBoolean(request.getParameter("isill")));

        if (request.getParameter("id").isEmpty()) {
            patient.setIll(true);
            log.info("create {} for user {}", patient, doctorId);
            checkNew(patient);
            service.create(patient, doctorId);
        } else {
            log.info("update {} for user {}", patient, doctorId);
            assureIdConsistent(patient, getId(request));
            service.update(patient, doctorId);
        }
        return "redirect:/patients";
    }




}
