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
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionDAO dao;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionDAO dao) {
        this.dao = dao;
    }

    @Override
    public Prescription get(int id) throws NotFoundException {
        return checkNotFoundWithId(dao.get(id), id);
    }

    @Override
    public Prescription getWithId(int id, int patientId) throws NotFoundException {
        return checkNotFoundWithId(dao.getWithId(id, patientId), id);
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
