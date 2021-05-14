package com.tsystems.javaschool.service.patient;

import com.tsystems.javaschool.DAO.PatientDAO;
import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.util.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientDAO dao;

    @Autowired
    public PatientServiceImpl(PatientDAO dao) {
        this.dao = dao;
    }

    @Override
    public Patient get(int id) throws NotFoundException {
        return dao.get(id);
    }

    //TODO добавить сюда сервис назначений, удалить оттуда назначения человека
    @Override
    @Transactional
    public void delete(int id) throws NotFoundException {
        Patient patient = dao.get(id);
        patient.setIll(false);
        patient.setPrescriptions(null);
        patient.setDiagnosis("Здоров");
        update(patient, patient.getDoctor().getId());
    }

    @Override
    public List<Patient> getAll() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public void update(Patient patient, int userId) throws NotFoundException {
        dao.save(patient, userId);
    }

    @Override
    @Transactional
    public Patient create(Patient patient, int userId) {
        return dao.save(patient, userId);
    }
}
