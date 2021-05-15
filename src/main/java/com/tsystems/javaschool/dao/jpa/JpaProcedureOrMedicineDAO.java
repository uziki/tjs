package com.tsystems.javaschool.dao.jpa;

import com.tsystems.javaschool.dao.ProcedureOrMedicineDAO;
import com.tsystems.javaschool.model.ProcedureOrMedicine;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaProcedureOrMedicineDAO implements ProcedureOrMedicineDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ProcedureOrMedicine save(ProcedureOrMedicine pom) {
        if (pom.isNew()) {
            em.persist(pom);
            return pom;
        } else {
            return em.merge(pom);
        }
    }

    @Override
    public ProcedureOrMedicine get(int id) {
        return em.find(ProcedureOrMedicine.class, id);
    }

    @Override
    public List<ProcedureOrMedicine> getAll() {
        return em.createNamedQuery(ProcedureOrMedicine.ALL, ProcedureOrMedicine.class).getResultList();
    }

    @Override
    public ProcedureOrMedicine getByName(String name) {
        return em.createNamedQuery(ProcedureOrMedicine.BY_NAME, ProcedureOrMedicine.class)
                .setParameter("name", name)
                .getSingleResult();

    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(ProcedureOrMedicine.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }
}
