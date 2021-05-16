package com.tsystems.javaschool.web;

import com.tsystems.javaschool.model.Prescription;
import com.tsystems.javaschool.model.PrescriptionType;
import com.tsystems.javaschool.model.ProcedureOrMedicine;
import com.tsystems.javaschool.service.PrescriptionEventFacade;
import com.tsystems.javaschool.service.prescription.PrescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.tsystems.javaschool.util.ControllerUtil.*;
import static com.tsystems.javaschool.util.ValidationUtil.assureIdConsistent;
import static com.tsystems.javaschool.util.ValidationUtil.checkNew;


@Controller
@RequestMapping("/patients/prescriptions")
public class PrescriptionController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private PrescriptionEventFacade facadeService;
    private PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService, PrescriptionEventFacade facadeService) {
        this.prescriptionService = prescriptionService;
        this.facadeService = facadeService;
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        String type = getType(request);
        model.addAttribute("prescription", new Prescription());
        model.addAttribute("patientId", request.getParameter("patientid"));
        if (type.equals("medicine")) {
            return "medicineForm";
        } else if (type.equals("procedure")){
            return "procedureForm";
        }
        return "prescriptions";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        log.info("get prescription {}", getId(request));
        Prescription prescription = prescriptionService.get(getId(request));
        model.addAttribute("prescription", prescription);
        model.addAttribute("patientId", prescription.getPatient().id());
        addTimePatternToModel(prescription, model);
        if (prescription.getProcedureOrMedicine().getPrescriptionType() == PrescriptionType.TYPE_MEDICINE) {
            return "medicineForm";
        } else
            return "procedureForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int doctorId = SecurityUtil.authUserId();
        int patientId = Integer.parseInt(request.getParameter("patientid"));
        int timePeriod = Integer.parseInt(request.getParameter("timeperiod"));
        int dose = Integer.parseInt(request.getParameter("dose"));
        String pomName = request.getParameter("name");
        String pomType = request.getParameter("type");
        String timePattern = timePatternFromRequest(request);
        Prescription prescription = new Prescription();
        prescription.setDose(dose);
        prescription.setTimePeriod(timePeriod);
        prescription.setTimePattern(timePattern);
        prescription.setActive(true);
        if (request.getParameter("id").isEmpty()) {
            log.info("create {} for patient {} from doctor {}", prescription, patientId, doctorId);
            checkNew(prescription);
            facadeService.createPrescription(prescription, patientId, doctorId, pomName, pomType);
        } else {
            assureIdConsistent(prescription, getId(request));
            log.info("update {} for patient {} from doctor {}", prescription, patientId, doctorId);
            facadeService.updatePrescription(prescription, patientId, doctorId, pomName, pomType);
        }
        redirectAttributes.addAttribute("id", patientId);
        return "redirect:/patients/prescriptions";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int id = getId(request);
        int patientId = Integer.parseInt(request.getParameter("patientid"));
        log.info("delete prescription {} for patient {}", id, patientId);
        facadeService.deletePrescription(id, patientId);
        redirectAttributes.addAttribute("id", patientId);
        return "redirect:/patients/prescriptions";
    }
}
