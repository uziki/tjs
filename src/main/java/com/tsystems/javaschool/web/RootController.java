package com.tsystems.javaschool.web;

import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.model.Role;
import com.tsystems.javaschool.service.patient.PatientService;
import com.tsystems.javaschool.service.prescription.PrescriptionService;
import com.tsystems.javaschool.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static com.tsystems.javaschool.util.ControllerUtil.getId;
import static com.tsystems.javaschool.web.SecurityUtil.authUserId;
import static com.tsystems.javaschool.web.SecurityUtil.setAuthUserName;

@Controller
public class RootController {
    private PatientService patientService;
    private PrescriptionService prescriptionService;
    private UserService userService;

    @Autowired
    public void setService(PatientService patientService, PrescriptionService prescriptionService,
                           UserService userService) {
        this.patientService = patientService;
        this.prescriptionService = prescriptionService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String root() {
        return (userService.get(authUserId()).getRole().equals(Role.DOCTOR) ?
                "redirect:patients" : "redirect:events");
    }

    @GetMapping("/patients")
    public String getPatients(Model model) {
        model.addAttribute("patients", patientService.getAll());
        setAuthUserName(model);
        return "patients";
    }

    @GetMapping("/patients/prescriptions")
    public String getPrescriptions(HttpServletRequest request, Model model) {
        Patient patient = patientService.get(getId(request));
        model.addAttribute("patient", patient);
        model.addAttribute("prescriptions", prescriptionService.getAllWithId(patient.getId()));
        setAuthUserName(model);
        return "prescriptions";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
