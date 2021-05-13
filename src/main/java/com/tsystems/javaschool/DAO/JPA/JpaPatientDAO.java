package com.tsystems.javaschool.DAO.JPA;

import com.tsystems.javaschool.DAO.PatientDAO;
import com.tsystems.javaschool.model.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaPatientDAO implements PatientDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Patient save(Patient patient) {
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

    @Override
    @Transactional
    public boolean delete(int id) {

/*      User ref = em.getReference(User.class, id);
        em.remove(ref);
*/
        return em.createNamedQuery(Patient.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }


    @Override
    public List<Patient> getAll() {
        return em.createNamedQuery(Patient.ALL_SORTED, Patient.class).getResultList();
    }
}
