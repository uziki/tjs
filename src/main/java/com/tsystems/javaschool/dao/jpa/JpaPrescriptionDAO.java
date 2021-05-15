package com.tsystems.javaschool.dao.jpa;

import com.tsystems.javaschool.dao.PrescriptionDAO;
import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.model.Prescription;
import com.tsystems.javaschool.model.ProcedureOrMedicine;
import com.tsystems.javaschool.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaPrescriptionDAO implements PrescriptionDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Prescription save(Prescription prescription) {
        ProcedureOrMedicine pom = prescription.getProcedureOrMedicine();
        em.persist(pom);
        if (prescription.isNew()) {
            em.persist(prescription);
            return prescription;
        } else {
            return em.merge(prescription);
        }
    }

    @Override
    public Prescription get(int id) {
        return em.find(Prescription.class, id);
    }

    @Override
    public boolean delete(int id) {

        return em.createNamedQuery(Prescription.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }


    @Override
    public List<Prescription> getAll() {
        return em.createNamedQuery(Prescription.ALL, Prescription.class).getResultList();
    }

    @Override
    public List<Prescription> getAllWithId(int patientId) {
        return em.createNamedQuery(Prescription.ALL_WITH_ID, Prescription.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

    @Override
    public Prescription saveWithData(Prescription prescription, int patientId, int doctorId) {
        prescription.setDoctor(em.getReference(User.class, doctorId));
        prescription.setPatient(em.getReference(Patient.class, patientId));
        return save(prescription);
    }
}
