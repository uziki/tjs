package com.tsystems.javaschool.DAO.JPA;

import com.tsystems.javaschool.DAO.PatientDAO;
import com.tsystems.javaschool.model.Patient;
import com.tsystems.javaschool.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaPatientDAO implements PatientDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Patient save(Patient patient, int userId ) {
        patient.setDoctor(em.getReference(User.class, userId));
        if (patient.isNew()) {
            em.persist(patient);
            return patient;
        } else {
            return em.merge(patient);
        }
    }

    @Override
    public Patient get(int id) {
        return em.find(Patient.class, id);
    }

    /*@Override
    public boolean delete(int id) {

        return em.createNamedQuery(Patient.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }*/


    @Override
    public List<Patient> getAll() {
        return em.createNamedQuery(Patient.ALL_SORTED, Patient.class).getResultList();
    }
}
