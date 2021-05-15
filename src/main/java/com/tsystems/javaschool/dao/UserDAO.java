package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return true;
        } else {
            return false;
        }
    }
}
