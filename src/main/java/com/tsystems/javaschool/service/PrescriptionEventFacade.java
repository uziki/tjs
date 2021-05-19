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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.tsystems.javaschool.util.DateTimeUtil.timePatternToDates;
import static com.tsystems.javaschool.web.SecurityUtil.authUserId;

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

    public Prescription createPrescription(Prescription prescription, int patientId, int doctorId,
                                           String pomName, String pomType) {
        ProcedureOrMedicine pom = pomService.createWithNameAndType(pomName, pomType);
        prescription.setProcedureOrMedicine(pom);
        prescription.setDoctor(userService.get(doctorId));
        prescription.setPatient(patientService.get(patientId));
        prescriptionService.create(prescription);
        List<Event> events = new ArrayList<>();
        prescription.setEventList(createEvents(prescription, events));
        prescriptionService.update(prescription);
        return prescription;
    }

    public void updatePrescription(Prescription prescription, int patientId, int doctorId,
                                   String pomName, String pomType) {
        ProcedureOrMedicine pom = pomService.createWithNameAndType(pomName, pomType);
        prescription.setProcedureOrMedicine(pom);
        prescription.setDoctor(userService.get(doctorId));
        prescription.setPatient(patientService.get(patientId));
        List<Event> events = eventService.getByPrescriptionId(prescription.getId());
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event event = iterator.next();
            if (event.getEventStatus().toString().equals("STATUS_PLANNED")) {
                eventService.delete(event.getId());
            }
        }
        prescription.setEventList(createEvents(prescription, events));
        prescriptionService.update(prescription);
    }

    public void deletePrescription(int id, int patientId) throws NotFoundException, NoResultException {
        Prescription prescription = prescriptionService.getWithId(id, patientId);
        prescription.setActive(false);
        for (Event event : prescription.getEventList()) {
            if (event.getEventStatus() == EventStatus.STATUS_PLANNED) {
                event.setEventStatus(EventStatus.STATUS_CANCELED);
                event.setMessage("Canceled by doctor " + userService.get(authUserId()).getName());
                eventService.update(event);
            }
        }
        prescriptionService.update(prescription);
    }

    public void deletePatient(int id) throws NotFoundException {
        Patient patient = patientService.get(id);
        patient.setIll(false);
        for (Prescription prescription : patient.getPrescriptions()) {
            if (prescription.isActive()) {
                deletePrescription(prescription.getId(), patient.getId());
            }
        }
        patient.setDiagnosis("Healthy");
        patientService.update(patient, patient.getDoctor().getId());
    }

    public List<Event> createEvents(Prescription prescription, List<Event> events) {
        List<LocalDateTime> ldts = timePatternToDates(prescription.getTimePattern(), prescription.getTimePeriod(),
                prescription.getProcedureOrMedicine().getPrescriptionType());
        for (LocalDateTime ldt : ldts) {
            Event event = new Event(prescription, ldt);
            eventService.create(event);
            events.add(event);
        }
        return events;
    }
}
