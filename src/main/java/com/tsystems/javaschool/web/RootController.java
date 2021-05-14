package com.tsystems.javaschool.web;

import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.service.patient.PatientService;
import com.tsystems.javaschool.service.prescription.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.tsystems.javaschool.util.ControllerUtil.getId;

@Controller
public class RootController {
    private PatientService patientService;
    private PrescriptionService prescriptionService;

    @Autowired
    public void setService(PatientService patientService, PrescriptionService prescriptionService) {
        this.patientService = patientService;
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/patients")
    public String getPatients(Model model) {
        model.addAttribute("patients", patientService.getAll());
        return "patients";
    }

    @GetMapping("/events")
    public String getEvents(Model model) {
        //model.addAttribute("events", dao.getAll());
        return "events";
    }

    @GetMapping("/patients/prescriptions")
    public String getPrescriptions(HttpServletRequest request, Model model) {
        Patient patient = patientService.get(getId(request));
        model.addAttribute("patient", patient);
        model.addAttribute("prescriptions", prescriptionService.getAllWithId(patient.getId()));
        return "prescriptions";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
