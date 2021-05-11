package com.tsystems.javaschool.DAO.Patient;

import com.tsystems.javaschool.model.Patient;

import java.util.List;

public interface PatientDAO {
    // null if not found, when updated
    Patient save(Patient patient);

    // false if not found
    boolean delete(int id);

    // null if not found
    Patient get(int id);

    List<Patient> getAll();
}
