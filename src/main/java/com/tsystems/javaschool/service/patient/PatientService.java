package com.tsystems.javaschool.service.patient;

import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.util.Exception.NotFoundException;

import java.util.List;

public interface PatientService {
    Patient get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Patient> getAll();

    void update(Patient patient) throws NotFoundException;

    Patient create(Patient patient);
}
