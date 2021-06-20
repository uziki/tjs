package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.User;

/**
 * User DAO interface.
 */
public interface UserDAO {

    /**
     * Creates or updates User.
     *
     * @param user need to be saved
     * @return saved User
     */
    User save(User user);

    /**
     * Gets User by id.
     *
     * @param id of User
     * @return User by id
     */
    User get(int id);

    /**
     * Gets User by id.
     *
     * @param email of User
     * @return User by email
     */
    User getByEmail(String email);
}
