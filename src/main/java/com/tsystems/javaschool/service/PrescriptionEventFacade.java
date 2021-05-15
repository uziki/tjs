package com.tsystems.javaschool.service;


import com.tsystems.javaschool.model.*;
import com.tsystems.javaschool.service.event.EventService;
import com.tsystems.javaschool.service.patient.PatientService;
import com.tsystems.javaschool.service.prescription.PrescriptionService;
import com.tsystems.javaschool.service.procedureOrMedicineService.ProcedureOrMedicineService;
import com.tsystems.javaschool.service.user.UserService;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

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

    public Prescription createPrescription(Prescription prescription, int patientId, int doctorId,
                                           String pomName, String pomType) {
        ProcedureOrMedicine pom = pomService.createWithNameAndType(pomName, pomType);
        prescription.setProcedureOrMedicine(pom);
        prescription.setDoctor(userService.get(doctorId));
        prescription.setPatient(patientService.get(patientId));
        return prescriptionService.create(prescription);
    }

    public void updatePrescription(Prescription prescription, int patientId, int doctorId,
                                   String pomName, String pomType) {
        ProcedureOrMedicine pom = pomService.createWithNameAndType(pomName, pomType);
        prescription.setProcedureOrMedicine(pom);
        prescription.setDoctor(userService.get(doctorId));
        prescription.setPatient(patientService.get(patientId));
        prescriptionService.update(prescription);
    }

    public void deletePrescription(int id, int patientId) throws NotFoundException, NoResultException {
        Prescription prescription = prescriptionService.getWithId(id, patientId);
        prescription.setActive(false);
        for (Event event : prescription.getEventList()) {
            if (event.getEventStatus() == EventStatus.PLANNED) {
                event.setEventStatus(EventStatus.CANCELED);
                eventService.update(event);
            }
        }
        prescriptionService.update(prescription);
    }

    public void deletePatient(int id) throws NotFoundException {
        Patient patient = patientService.get(id);
        patient.setIll(false);
        for (Prescription prescription : patient.getPrescriptions()) {
            if(prescription.isActive()) {
                deletePrescription(prescription.getId(), patient.getId());
            }
        }
        patient.setDiagnosis("Здоров");
        patientService.update(patient, patient.getDoctor().getId());
    }
}
