package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.Patient;

import java.util.List;

public interface PatientDAO {
    Patient save(Patient patient, int doctorId);

    Patient get(int id);

    List<Patient> getAll();

}
