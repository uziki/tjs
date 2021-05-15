package com.tsystems.javaschool.service;


import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.model.Prescription;
import com.tsystems.javaschool.model.ProcedureOrMedicine;
import com.tsystems.javaschool.model.User;
import com.tsystems.javaschool.service.event.EventService;
import com.tsystems.javaschool.service.patient.PatientService;
import com.tsystems.javaschool.service.prescription.PrescriptionService;
import com.tsystems.javaschool.service.procedureOrMedicineService.ProcedureOrMedicineService;
import com.tsystems.javaschool.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrescriptionEventFacade {
    private PrescriptionService prescriptionService;
    private EventService eventService;
    private ProcedureOrMedicineService pomService;
    private PatientService patientService;
    private UserService userService;

    @Autowired
    public PrescriptionEventFacade(PrescriptionService prescriptionService, EventService eventService,
                                   ProcedureOrMedicineService pomService, PatientService patientService,
                                   UserService userService) {
        this.prescriptionService = prescriptionService;
        this.eventService = eventService;
        this.pomService = pomService;
        this.patientService = patientService;
        this.userService = userService;
    }

    //TODO при изменении назначения - сохранять врача, изменить назначение может новый врач
    //TODO при создании/изменении назначения - проверять, может такой пом уже есть в базе
    //TODO delete prescriptions
    //TODO prescriptions procedure

    public Prescription createPrescription(Prescription prescription, int patientId, int doctorId,
                                           String pomName, String pomType) {
        ProcedureOrMedicine pom = pomService.getByName(pomName);
        prescription.setDoctor(userService.get(doctorId));
        prescription.setPatient(patientService.get(patientId));
        return prescriptionService.create(prescription);
    }

    public void updatePrescription(Prescription prescription, int patientId, int doctorId, String pomName, String pomType) {

        prescription.setDoctor(userService.get(doctorId));
        prescription.setPatient(patientService.get(patientId));
        prescriptionService.update(prescription);
    }
}
