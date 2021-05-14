package com.tsystems.javaschool.web;

import com.tsystems.javaschool.model.Prescription;
import com.tsystems.javaschool.service.prescription.PrescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.tsystems.javaschool.util.ControllerUtil.getId;
import static com.tsystems.javaschool.util.ControllerUtil.getType;


@Controller
@RequestMapping("/patients/prescriptions")
public class PrescriptionController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private PrescriptionService service;

    @Autowired
    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        String type = getType(request);
        model.addAttribute("prescription", new Prescription());
        if (type.equals("medicine")) {
            return "medicineForm";
        } else if (type.equals("procedure")){
            return "prescriptionForm";
        }
        return "prescriptions";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        log.info("get prescription {}", getId(request));
        Prescription prescription = service.get(getId(request));
        model.addAttribute("prescription", prescription);
        return "prescriptionForm";
    }
}
