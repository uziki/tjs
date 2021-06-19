package com.tsystems.javaschool.dao.jpa;


import com.tsystems.javaschool.dao.UserDAO;
import com.tsystems.javaschool.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaUserDAO implements UserDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        return em.createNamedQuery(User.GET_BY_EMAIL, User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
