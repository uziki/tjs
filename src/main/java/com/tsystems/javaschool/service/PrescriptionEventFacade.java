package com.tsystems.javaschool.service;


import com.tsystems.javaschool.model.*;
import com.tsystems.javaschool.service.event.EventService;
import com.tsystems.javaschool.service.patient.PatientService;
import com.tsystems.javaschool.service.prescription.PrescriptionService;
import com.tsystems.javaschool.service.procedureOrMedicineService.ProcedureOrMedicineService;
import com.tsystems.javaschool.service.user.UserService;
import com.tsystems.javaschool.util.MQSender;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.tsystems.javaschool.util.DateTimeUtil.DAYS_BEFORE_TOMORROW;
import static com.tsystems.javaschool.util.DateTimeUtil.timePatternToDates;

/**
 * Prescription & event facade service for operations with different entity types
 */
@Service
@Transactional
public class PrescriptionEventFacade {
    private static final String HEALTHY = "Healthy";
    private static final String CANCELED_BY_DOCTOR = "Canceled by doctor";
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

    /**
     * Creates prescription and relevant events
     *
     * @param prescription needed to be created
     * @param patientId    of relevant Patient
     * @param doctorId     Doctor, who made prescription
     * @param pomName      ProcedureOrMedicine name
     * @param pomType      ProcedureOrMedicine type
     * @return saved Prescription
     */
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

    /**
     * Updates prescription and relevant events. Send message to MQ if at least one updated event has today's date
     *
     * @param prescription needed to be created
     * @param patientId    of relevant Patient
     * @param doctorId     Doctor, who made prescription
     * @param pomName      ProcedureOrMedicine name
     * @param pomType      ProcedureOrMedicine type
     */
    public void updatePrescription(Prescription prescription, int patientId, int doctorId,
                                   String pomName, String pomType) {
        ProcedureOrMedicine pom = pomService.createWithNameAndType(pomName, pomType);
        prescription.setProcedureOrMedicine(pom);
        prescription.setDoctor(userService.get(doctorId));
        prescription.setPatient(patientService.get(patientId));
        List<Event> events = eventService.getByPrescriptionId(prescription.getId());
        Iterator<Event> iterator = events.iterator();
        boolean isTodayEvent = false;
        while (iterator.hasNext()) {
            Event event = iterator.next();
            if (event.getEventStatus().toString().equals("STATUS_PLANNED")) {
                eventService.delete(event.getId());
                if (event.getDateTime().isBefore(LocalDate.now().plusDays(DAYS_BEFORE_TOMORROW).atStartOfDay())) {
                    isTodayEvent = true;
                }
            }
        }
        if (isTodayEvent) MQSender.sendMessage();
        prescription.setEventList(createEvents(prescription, events));
        prescriptionService.update(prescription);
    }

    /**
     * Cancels specified prescription and cancels all undone relevant events. Send message to MQ
     * if at least one canceled event had today's date
     *
     * @param id        of prescription to be canceled
     * @param patientId of relevant Patient
     * @throws NotFoundException if not found such prescription
     */
    public void deletePrescription(int id, int patientId) throws NotFoundException {
        Prescription prescription = prescriptionService.getWithId(id, patientId);
        prescription.setActive(false);
        boolean isTodayEvent = false;
        for (Event event : prescription.getEventList()) {
            if (event.getEventStatus() == EventStatus.STATUS_PLANNED) {
                event.setEventStatus(EventStatus.STATUS_CANCELED);
                event.setMessage(CANCELED_BY_DOCTOR);
                eventService.update(event);
                if (event.getDateTime().isBefore(LocalDate.now().plusDays(DAYS_BEFORE_TOMORROW).atStartOfDay()))
                    isTodayEvent = true;
            }
        }
        if (isTodayEvent) MQSender.sendMessage();
        prescriptionService.update(prescription);
    }

    /**
     * Discharges patient. Cancels all his prescriptions and relevant events
     *
     * @param id of patient to be discharge
     * @throws NotFoundException if not found
     */
    public void deletePatient(int id) throws NotFoundException {
        Patient patient = patientService.get(id);
        patient.setIll(false);
        for (Prescription prescription : patient.getPrescriptions()) {
            if (prescription.isActive()) {
                deletePrescription(prescription.getId(), patient.getId());
            }
        }
        patient.setDiagnosis(HEALTHY);
        patientService.update(patient, patient.getDoctor().getId());
    }

    /**
     * Creates or updates events according to specified prescription. Send message to MQ if
     * at least one created or updated event has today's date
     *
     * @param prescription to create events
     * @param events       list of events to be filled/updated
     * @return Events
     */
    public List<Event> createEvents(Prescription prescription, List<Event> events) {
        List<LocalDateTime> ldts = timePatternToDates(prescription.getTimePattern(), prescription.getTimePeriod(),
                prescription.getProcedureOrMedicine().getPrescriptionType());
        boolean isTodayEvent = false;
        for (LocalDateTime ldt : ldts) {
            Event event = new Event(prescription, ldt);
            eventService.create(event);
            events.add(event);
            if (ldt.isBefore(LocalDate.now().plusDays(DAYS_BEFORE_TOMORROW).atStartOfDay())) isTodayEvent = true;
        }
        if (isTodayEvent) MQSender.sendMessage();
        return events;
    }
}
