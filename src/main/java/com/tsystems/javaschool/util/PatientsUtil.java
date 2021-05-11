package com.tsystems.javaschool.util;

import com.tsystems.javaschool.model.*;

import java.util.Arrays;
import java.util.List;

public class PatientsUtil {

    /*public static final List<Patient> patients = Arrays.asList(
            new Patient("Иванов Иван", "Перелом пупка", "1111", true),
            new Patient("Петров Петр", "Вывих глаза", "2222", true),
            new Patient("Сидоров Сидр", "Не жилец", "3333", true)
    );*/



    public static final List<Patient> patients = getPatients();

    public static List<Patient> getPatients() {
        ProcedureOrMedicine pom3 = new ProcedureOrMedicine("Аспирин", PrescriptionType.TYPE_MEDICINE);
        ProcedureOrMedicine pom2 = new ProcedureOrMedicine("Зарядка", PrescriptionType.TYPE_PROCEDURE);
        ProcedureOrMedicine pom1 = new ProcedureOrMedicine("Массаж", PrescriptionType.TYPE_PROCEDURE);

        Patient patient1 = new Patient("Иванов Иван", "Перелом пупка", "1111", true);
        Patient patient2 = new Patient("Петров Петр", "Вывих глаза", "2222", true);
        Patient patient3 = new Patient("Сидоров Сидр", "Не жилец", "3333", true);

        Prescription ps1 = new Prescription(patient1, "по четвергам и пятницам", 2, pom1);
        Prescription ps2 = new Prescription(patient2, "по понедельникам и воскресеньям", 3, pom2);
        Prescription ps3 = new Prescription(patient3, "утром и вечером", 2, pom3, 3);

        patient1.setPrescriptions(Arrays.asList(ps1, ps2));
        patient2.setPrescriptions(Arrays.asList(ps2, ps3));
        patient3.setPrescriptions(Arrays.asList(ps3, ps1));

        return Arrays.asList(patient1, patient2, patient3);
    }
}