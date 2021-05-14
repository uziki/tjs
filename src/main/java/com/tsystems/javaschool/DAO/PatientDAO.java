package com.tsystems.javaschool.DAO;

import com.tsystems.javaschool.model.Patient;

import java.util.List;

public interface PatientDAO {
    Patient save(Patient patient, int userId);

    //boolean delete(int id);

    Patient get(int id);

    List<Patient> getAll();

}
