package com.tsystems.javaschool.service.patient;

import com.tsystems.javaschool.DAO.PatientDAO;
import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.util.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public void delete(int id) throws NotFoundException {
        dao .delete(id);
    }

    @Override
    public List<Patient> getAll() {
        return dao.getAll();
    }

    @Override
    public void update(Patient patient) throws NotFoundException {
        dao.save(patient);
    }

    @Override
    public Patient create(Patient patient) {
        return dao.save(patient);
    }
}
