package com.tsystems.javaschool.service;

import com.tsystems.javaschool.model.*;
import com.tsystems.javaschool.service.event.EventService;
import com.tsystems.javaschool.service.patient.PatientService;
import com.tsystems.javaschool.service.prescription.PrescriptionService;
import com.tsystems.javaschool.service.procedureOrMedicineService.ProcedureOrMedicineService;
import com.tsystems.javaschool.service.user.UserService;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

import static com.tsystems.javaschool.model.AbstractBaseEntity.START_SEQ;
import static com.tsystems.javaschool.service.TestData.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

@ContextConfiguration({
        "classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PrescriptionEventFacadeTest {
    @Autowired
    private PrescriptionEventFacade facadeService;
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ProcedureOrMedicineService pomService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private UserService userService;


    @Test
    public void createPrescription() {
        Prescription prescription = new Prescription();
        prescription.setDose(2);
        prescription.setTimePattern("1-10:50 2- 3-");
        prescription.setTimePeriod(3);
        prescription.setActive(true);
        prescription = facadeService.createPrescription(
                prescription, START_SEQ + 3, START_SEQ, "Aspirin", "TYPE_MEDICINE");
        List<Event> events = prescription.getEventList();
        for (Event e : events) {
            assertMatch(e.getDose(), 2);
            assertMatch(e.getEventStatus(), EventStatus.STATUS_PLANNED);
            assertMatch(e.getPrescription(), prescription);
            assertMatch(e.getProcedureOrMedicine().getPrescriptionType(), PrescriptionType.TYPE_MEDICINE);
        }
        assertMatch(events.size(), 3);
    }

    @Test
    public void updatePrescription() {
        Prescription prescription = new Prescription(getExpectedPrescription1());
        prescription.setDose(5);
        facadeService.updatePrescription(prescription, prescription.getPatient().getId(), prescription.getDoctor().getId(),
                prescription.getProcedureOrMedicine().getName(), prescription.getProcedureOrMedicine().getPrescriptionType().name());
        prescription = prescriptionService.get(prescription.getId());
        List<Event> events = prescription.getEventList();
        for (Event e : events) {
            assertMatch(e.getDose(), 5);
        }
    }

    @Test
    public void deletePrescription() {
        facadeService.deletePrescription(START_SEQ + 7, START_SEQ + 3);
        Prescription prescription = prescriptionService.get(START_SEQ + 7);
        assertFalse(prescription.isActive());
        for (Event e : prescription.getEventList()) {
            assertMatch(e.getEventStatus(), EventStatus.STATUS_CANCELED);
            assertMatch(e.getMessage(), CANCELED_BY_DOCTOR);
        }
    }

    @Test
    public void deletePrescriptionNotFound() {
        assertThrows(NoResultException.class, () -> facadeService.deletePrescription(START_SEQ + 7, START_SEQ + 300));
        assertThrows(NoResultException.class, () -> facadeService.deletePrescription(START_SEQ + 300, START_SEQ + 3));
    }

    @Test
    public void deletePatient() {
        facadeService.deletePatient(START_SEQ + 3);
        Patient patient = patientService.get(START_SEQ + 3);
        List<Prescription> prescriptions = patient.getPrescriptions();
        for (Prescription p : prescriptions) {
            assertFalse(p.isActive());
            for (Event e : p.getEventList()) {
                assertMatch(e.getEventStatus(), EventStatus.STATUS_CANCELED);
            }
        }
        assertMatch(patient.getDiagnosis(), HEALTHY);
        assertMatch(patient.isIll(), false);
    }

    @Test
    public void deletePatientNotFound() {
        assertThrows(NotFoundException.class, () -> facadeService.deletePatient(START_SEQ + 300));
    }

    @Test
    public void createEvents() {
        Prescription prescription = new Prescription(getExpectedPrescription1());
        prescription.setDose(5);
        List<Event> events = new ArrayList<>();
        events = facadeService.createEvents(prescription, events);
        for (Event e : events) {
            assertMatch(e.getDose(), 5);
        }
    }
}