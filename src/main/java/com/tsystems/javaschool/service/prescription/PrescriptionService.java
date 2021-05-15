package com.tsystems.javaschool.service.prescription;

import com.tsystems.javaschool.model.Prescription;
import com.tsystems.javaschool.util.exception.NotFoundException;

import java.util.List;

public interface PrescriptionService {
    Prescription get(int id) throws NotFoundException;

    Prescription getWithId (int id, int patientId) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Prescription> getAll();

    List<Prescription> getAllWithId(int patientId);

    void update(Prescription prescription) throws NotFoundException;

    Prescription create(Prescription prescription);
}
