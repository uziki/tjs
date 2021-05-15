package com.tsystems.javaschool.web;

import com.tsystems.javaschool.model.Prescription;
import com.tsystems.javaschool.model.PrescriptionType;
import com.tsystems.javaschool.model.ProcedureOrMedicine;
import com.tsystems.javaschool.service.prescription.PrescriptionService;
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
import static com.tsystems.javaschool.util.ControllerUtil.getType;
import static com.tsystems.javaschool.util.ValidationUtil.assureIdConsistent;
import static com.tsystems.javaschool.util.ValidationUtil.checkNew;


@Controller
@RequestMapping("/patients/prescriptions")
public class PrescriptionController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private PrescriptionService prescriptionService;


    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        String type = getType(request);
        model.addAttribute("prescription", new Prescription());
        model.addAttribute("patientId", request.getParameter("patientid"));
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
        Prescription prescription = prescriptionService.get(getId(request));
        model.addAttribute("prescription", prescription);
        model.addAttribute("patientId", prescription.getPatient().id());
        if (prescription.getProcedureOrMedicine().getPrescriptionType() == PrescriptionType.TYPE_MEDICINE) {
            return "medicineForm";
        } else
            return "prescriptionForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        int doctorId = SecurityUtil.authUserId();
        int patientId = Integer.parseInt(request.getParameter("patientid"));
        int dose = Integer.parseInt(request.getParameter("dose"));
        int timePeriod = Integer.parseInt(request.getParameter("timeperiod"));
        String timePattern = "1:" + request.getParameter("morning")
                + " 2:" + request.getParameter("afternoon")
                + " 3:" + request.getParameter("evening");
        ProcedureOrMedicine pom = new ProcedureOrMedicine(request.getParameter("name"),
                PrescriptionType.valueOf(request.getParameter("type")));
        Prescription prescription = new Prescription();
        prescription.setProcedureOrMedicine(pom);
        prescription.setDose(dose);
        prescription.setTimePeriod(timePeriod);
        prescription.setTimePattern(timePattern);
        prescription.setActive(true);
        if (request.getParameter("id").isEmpty()) {
            log.info("create {} for patient {} from doctor {}", prescription, patientId, doctorId);
            checkNew(prescription);
            prescriptionService.createWithData(prescription, patientId, doctorId);
        } else {
            log.info("update {} for patient {} from doctor {}", prescription, patientId, doctorId);
            assureIdConsistent(prescription, getId(request));
            prescriptionService.updateWithData(prescription, patientId, doctorId);
        }
        return "redirect:/patients";
    }


}
