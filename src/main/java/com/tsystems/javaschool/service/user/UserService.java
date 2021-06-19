package com.tsystems.javaschool.service.user;

import com.tsystems.javaschool.model.User;
import com.tsystems.javaschool.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    User get(int id) throws NotFoundException;

    User create(User user);
}