package com.tsystems.javaschool.service.prescription;

import com.tsystems.javaschool.dao.PrescriptionDAO;
import com.tsystems.javaschool.model.Prescription;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tsystems.javaschool.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService{

    private final PrescriptionDAO dao;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionDAO dao) {
        this.dao = dao;
    }

    @Override
    public Prescription get(int id) throws NotFoundException {
        return checkNotFoundWithId(dao.get(id), id);
    }


    //TODO реализовать отмену событий
    @Override
    @Transactional
    public void delete(int id) throws NotFoundException {
        Prescription prescription = get(id);
        prescription.setActive(false);
    }

    @Override
    public List<Prescription> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Prescription> getAllWithId(int patientId) {
        return dao.getAllWithId(patientId);
    }

    @Override
    @Transactional
    public void update(Prescription prescription) throws NotFoundException {
        checkNotFoundWithId(dao.save(prescription), prescription.getId());
    }

    @Override
    @Transactional
    public Prescription create(Prescription prescription) {
        return dao.save(prescription);
    }

}
