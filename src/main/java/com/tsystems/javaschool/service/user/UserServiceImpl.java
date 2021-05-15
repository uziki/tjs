package com.tsystems.javaschool.service.user;

import com.tsystems.javaschool.dao.UserDAO;
import com.tsystems.javaschool.model.User;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserDAO dao;

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public User get(int id) throws NotFoundException {
        return dao.get(id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        dao.delete(id);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void update(User user) throws NotFoundException {
        dao.save(user);
    }

    @Override
    public User create(User user) {
        return dao.save(user);
    }

}
