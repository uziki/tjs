package com.tsystems.javaschool.dao.jpa;

import com.tsystems.javaschool.dao.PrescriptionDAO;
import com.tsystems.javaschool.model.Prescription;
import com.tsystems.javaschool.model.ProcedureOrMedicine;
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
    public Prescription getWithId(int id, int patientId) {
        return em.createNamedQuery(Prescription.GET_WITH_ID, Prescription.class)
                .setParameter("id", id)
                .setParameter("patientId", patientId)
                .getSingleResult();
    }

    @Override
    public List<Prescription> getAllWithId(int patientId) {
        return em.createNamedQuery(Prescription.ALL_WITH_ID, Prescription.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }
}
