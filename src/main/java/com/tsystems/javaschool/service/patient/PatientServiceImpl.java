package com.tsystems.javaschool.service.patient;

import com.tsystems.javaschool.dao.PatientDAO;
import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tsystems.javaschool.util.ValidationUtil.checkNotFoundWithId;

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
        return checkNotFoundWithId(dao.get(id), id);
    }

    //TODO добавить сюда сервис назначений, удалить оттуда назначения человека
    @Override
    @Transactional
    public void delete(int id) throws NotFoundException {
        Patient patient = get(id);
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
    public void update(Patient patient, int doctorId) throws NotFoundException {
        checkNotFoundWithId(dao.save(patient, doctorId), patient.getId());
    }

    @Override
    @Transactional
    public Patient create(Patient patient, int doctorId) {
        return dao.save(patient, doctorId);
    }
}
