package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.Prescription;

import java.util.List;

public interface PrescriptionDAO {
    Prescription save(Prescription prescription);

    boolean delete(int id);

    Prescription get(int id);

    Prescription getWithId(int id, int patientId);

    List<Prescription> getAll();

    List<Prescription> getAllWithId(int patientId);
}
