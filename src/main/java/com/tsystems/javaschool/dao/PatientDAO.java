package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.Patient;

import java.util.List;

/**
 * Patient DAO interface.
 */
public interface PatientDAO {

    /**
     * Creates or updates Patient with current doctor id.
     *
     * @param patient  need to be saved
     * @param doctorId of doctor, who is saving patient
     * @return saved Patient
     */
    Patient save(Patient patient, int doctorId);

    /**
     * Gets Patient by id.
     *
     * @param id of Patient
     * @return Patient by id
     */
    Patient get(int id);

    /**
     * Gets all Patients.
     *
     * @return all patients
     */
    List<Patient> getAll();

}
