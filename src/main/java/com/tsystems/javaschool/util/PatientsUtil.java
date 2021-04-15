package com.tsystems.javaschool.util;

import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.model.User;

import java.util.Arrays;
import java.util.List;

public class PatientsUtil {

    public static final List<Patient> patients = Arrays.asList(
            new Patient("Иванов Иван", "Перелом пупка", "1111", UsersUtil.DOCTOR, true),
            new Patient("Петров Петр", "Вывих глаза", "2222", UsersUtil.DOCTOR, true),
            new Patient("Сидоров Сидр", "Не жилец", "3333", UsersUtil.DOCTOR, true)
    );

    public static List<Patient> getPatients () {
        return patients;
    }
}



