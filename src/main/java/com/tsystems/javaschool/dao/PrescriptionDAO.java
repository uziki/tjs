package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.Prescription;

import java.util.List;

/**
 * Prescription DAO interface.
 */
public interface PrescriptionDAO {

    /**
     * Creates or updates Prescription.
     *
     * @param prescription need to be saved
     * @return saved Prescription
     */
    Prescription save(Prescription prescription);

    /**
     * Gets Prescription by id.
     *
     * @param id of Prescription
     * @return Prescription by id
     */
    Prescription get(int id);

    /**
     * Gets Prescription by id and patient id.
     *
     * @param id        of Prescription
     * @param patientId of Patient
     * @return Prescription by id and patient id
     */
    Prescription getWithId(int id, int patientId);

    /**
     * Gets all Prescription for a specific patient.
     *
     * @param patientId of Patient
     * @return All prescription for this patient id
     */
    List<Prescription> getAllWithId(int patientId);
}
