package com.tsystems.javaschool.service.prescription;

import com.tsystems.javaschool.model.Prescription;
import com.tsystems.javaschool.util.exception.NotFoundException;

import java.util.List;

/**
 * Prescription service interface.
 */
public interface PrescriptionService {
    /**
     * Gets Prescription by id.
     *
     * @param id of Prescription
     * @return Prescription by id
     * @throws NotFoundException if not found
     */
    Prescription get(int id) throws NotFoundException;

    /**
     * Gets Prescription by id and patient id.
     *
     * @param id        of Prescription
     * @param patientId of Patient
     * @return Prescription by id and patient id
     * @throws NotFoundException if not found
     */
    Prescription getWithId(int id, int patientId) throws NotFoundException;

    /**
     * Gets all Prescription for a specific patient.
     *
     * @param patientId of Patient
     * @return All prescription for this patient id
     */
    List<Prescription> getAllWithId(int patientId);

    /**
     * Updates Prescription.
     *
     * @param prescription need to be updated
     * @throws NotFoundException if not found
     */
    void update(Prescription prescription) throws NotFoundException;

    /**
     * Creates Prescription.
     *
     * @param prescription need to be created
     * @return saved Prescription
     */
    Prescription create(Prescription prescription);
}
