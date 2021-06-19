package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.User;

import java.util.List;

public interface UserDAO {
    User save(User user);

    User get(int id);

    User getByEmail(String email);
}
