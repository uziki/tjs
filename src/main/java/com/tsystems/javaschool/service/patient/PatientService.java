package com.tsystems.javaschool.service.patient;

import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.util.exception.NotFoundException;

import java.util.List;

/**
 * Patient service interface.
 */
public interface PatientService {
    /**
     * Gets Patient by id.
     *
     * @param id of Patient
     * @return Patient by id
     * @throws NotFoundException if not found
     */
    Patient get(int id) throws NotFoundException;

    /**
     * Gets all Patients.
     *
     * @return all patients
     */
    List<Patient> getAll();

    /**
     * Updates Patient with current doctor id.
     *
     * @param patient  need to be updated
     * @param doctorId of doctor, who is updating patient
     * @throws NotFoundException if not found
     */
    void update(Patient patient, int doctorId) throws NotFoundException;

    /**
     * Creates Patient with current doctor id.
     *
     * @param patient  need to be created
     * @param doctorId of doctor, who is creating patient
     * @return saved patient
     */
    Patient create(Patient patient, int doctorId);
}
