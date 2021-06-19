package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.Prescription;

import java.util.List;

public interface PrescriptionDAO {
    Prescription save(Prescription prescription);

    Prescription get(int id);

    Prescription getWithId(int id, int patientId);

    List<Prescription> getAllWithId(int patientId);
}
