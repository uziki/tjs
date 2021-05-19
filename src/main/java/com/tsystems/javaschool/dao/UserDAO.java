package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.User;

import java.util.List;

public interface UserDAO {
    User save(User user);

    boolean delete(int id);

    User get(int id);

    List<User> getAll();

    User getByEmail(String email);
}
