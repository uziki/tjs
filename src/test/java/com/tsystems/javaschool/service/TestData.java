package com.tsystems.javaschool.service;

import com.tsystems.javaschool.model.*;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {
    public static final int START_ID = 1;
    public static final User user1 = new User(START_ID, "doctor@gmail.com", "{noop}doctor", "doctor1", Role.DOCTOR);
    public static final User user3 = new User(START_ID + 2, "doctor2@gmail.com", "{noop}doctor", "doctor2", Role.DOCTOR);
    public static final Patient patient1 = new Patient(START_ID + 3, "Иванов Иван", "Broken leg", "1111", true, user1);
    public static final Patient patient2 = new Patient(START_ID + 4, "Петров Петр", "Cold", "2222", true, user1);
    public static final Patient patient3 = new Patient(START_ID + 5, "Сидоров Сидр", "Gunshot wound", "3333", true, user3);
    public static final ProcedureOrMedicine pom = new ProcedureOrMedicine(START_ID + 6, "Aspirin", PrescriptionType.TYPE_MEDICINE);
    public static final Prescription prescription = new Prescription(START_ID + 9, patient1, user1, "1-10:50 2- 3-20:00", 1, pom, 2);


    public static User getNewUser() {
        return new User("a@a.ru", "12345", "new User", Role.NURSE);
    }

    public static <T> void assertMatch(T expected, T actual) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static <T> void assertMatchUsers(T expected, T actual) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("password", "patients").isEqualTo(expected);
    }
}
