package com.tsystems.javaschool.service.user;

import com.tsystems.javaschool.AuthorizedUser;
import com.tsystems.javaschool.dao.UserDAO;
import com.tsystems.javaschool.model.User;
import com.tsystems.javaschool.util.exception.NotFoundException;
import com.tsystems.javaschool.util.exception.NotUniqEmailException;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

import static com.tsystems.javaschool.util.ValidationUtil.checkNotFound;
import static com.tsystems.javaschool.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserDAO dao;

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = dao.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(dao.get(id), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dao.delete(id), id);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void update(User user) throws NotFoundException {
        checkNotFoundWithId(dao.save(user), user.getId());
    }

    @Override
    public User create(User user) throws NotUniqEmailException {
        try {
            User tryUser = dao.getByEmail(user.getEmail());
        } catch (NoResultException e) {
            return dao.save(user);
        }
        throw new NotUniqEmailException("not_uniq_email");
    }


}
