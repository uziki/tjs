package com.tsystems.javaschool.service;

import com.tsystems.javaschool.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tsystems.javaschool.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class TestData {
    public static final User user1 = new User(START_SEQ, "doctor@gmail.com", "{noop}doctor", "doctor1", Role.DOCTOR);
    public static final User user2 = new User(START_SEQ + 1, "doctor2@gmail.com", "{noop}doctor", "doctor2", Role.DOCTOR);
    public static final User user3 = new User(START_SEQ + 2, "nurse@gmail.com", "{noop}nurse", "nurse", Role.NURSE);
    public static final Patient patient1 = new Patient(START_SEQ + 3, "Иванов Иван", "Broken leg", "1111", true, user1);
    public static final Patient patient2 = new Patient(START_SEQ + 4, "Петров Петр", "Cold", "2222", true, user2);
    public static final ProcedureOrMedicine pom1 = new ProcedureOrMedicine(START_SEQ + 5, "Aspirin", PrescriptionType.TYPE_MEDICINE);
    public static final ProcedureOrMedicine pom2 = new ProcedureOrMedicine(START_SEQ + 6, "Massage", PrescriptionType.TYPE_PROCEDURE);
    public static final Prescription prescription1 = new Prescription(START_SEQ + 7, patient1, user1, "1-10:50 2- 3-", 1, pom1, 2);
    public static final Prescription prescription2 = new Prescription(START_SEQ + 8, patient2, user2, "1- 2- 3- 4-10:37 5- 6- 7-", 1, pom2, 0);
    private static final Event event1 = new Event(START_SEQ + 9, patient1, LocalDateTime.of(2020, 06, 21, 10, 50, 00), pom1, 2);
    private static final Event event2 = new Event(START_SEQ + 10, patient2, LocalDateTime.of(2020, 06, 24, 10, 37, 00), pom2, 0);


    public static User getExpectedUser1() {
        User user = user1;
        Patient patient = patient1;
        patient.setPrescriptions(Arrays.asList(getExpectedPrescription1()));
        user.setPatients(Arrays.asList(patient));
        return user;
    }

    public static User getExpectedUser2() {
        User user = user2;
        Patient patient = patient2;
        patient.setPrescriptions(Arrays.asList(getExpectedPrescription2()));
        user.setPatients(Arrays.asList(patient));
        return user;
    }

    public static User getNewUser() {
        return new User("a@a.ru", "12345", "new User", Role.NURSE);
    }

    public static Patient getExpectedPatient1() {
        Patient patient = patient1;
        patient.setDoctor(getExpectedUser1());
        return patient;
    }

    public static Patient getExpectedPatient2() {
        Patient patient = patient2;
        patient.setDoctor(getExpectedUser2());
        return patient;
    }

    public static Patient getNewPatient() {
        return new Patient("Name", "Diagnosis", "Insurance", true, getExpectedUser1());
    }

    public static Patient getUpdatedPatient() {
        Patient updated = new Patient(getExpectedPatient1());
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(new Prescription(updated.getPrescriptions().get(0)));
        List<Event> events = new ArrayList<>();
        events.add(new Event(prescriptions.get(0).getEventList().get(0)));
        updated.setInsuranceNumber("Updated insurance");
        updated.setName("Updated name");
        updated.setDiagnosis("Updated diagnosis");
        User user = updated.getDoctor();
        user.setPatients(Arrays.asList(updated));
        prescriptions.get(0).setPatient(updated);
        prescriptions.get(0).setDoctor(user);
        events.get(0).setPrescription(prescriptions.get(0));
        events.get(0).setPatient(updated);
        prescriptions.get(0).setEventList(events);
        updated.setPrescriptions(prescriptions);
        return updated;
    }

    public static Prescription getExpectedPrescription1() {
        Prescription prescription = prescription1;
        prescription.setActive(true);
        Event event = event1;
        event.setPrescription(prescription);
        prescription.setEventList(Arrays.asList(event));
        return prescription;
    }

    public static Prescription getExpectedPrescription2() {
        Prescription prescription = prescription2;
        prescription.setActive(true);
        Event event = event2;
        event.setPrescription(prescription);
        prescription.setEventList(Arrays.asList(event));
        return prescription;
    }

    public static Event getExpectedEvent1() {
        return getExpectedPatient1().getPrescriptions().get(0).getEventList().get(0);
    }

    public static Event getExpectedEvent2() {
        return getExpectedPatient2().getPrescriptions().get(0).getEventList().get(0);
    }


    public static Event getNewEvent() {
        return new Event(prescription2, LocalDateTime.now());
    }

    public static Event getUpdatedEvent() {
        Event updated = new Event(getExpectedEvent1());
        Prescription prescription = updated.getPrescription();
        updated.setDateTime(LocalDateTime.now());
        updated.setPatient(getExpectedPatient2());
        updated.setDose(50);
        updated.setProcedureOrMedicine(pom2);
        prescription.setEventList(Arrays.asList(updated));
        updated.setPrescription(prescription);
        return updated;
    }

    public static <T> void assertMatch(T expected, T actual) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static <T> void assertMatchLists(Iterable<T> expected, T... actual) {
        assertThat(expected).containsExactlyInAnyOrder(actual);
    }

    public static <T> void assertMatchIgnoringLDT(T expected, T actual) {
        assertThat(actual).usingRecursiveComparison().ignoringFieldsOfTypes(LocalDateTime.class).isEqualTo(expected);
    }
}
