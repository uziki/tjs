package com.tsystems.javaschool.service.user;

import com.tsystems.javaschool.model.User;
import com.tsystems.javaschool.util.exception.NotFoundException;

/**
 * User service interface
 */
public interface UserService {
    /**
     * Gets User by id.
     *
     * @param id of User
     * @return User by id
     * @throws NotFoundException if not found
     */
    User get(int id) throws NotFoundException;

    /**
     * Create User.
     *
     * @param user need to be saved
     * @return saved User
     */
    User create(User user);
}