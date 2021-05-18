package com.tsystems.javaschool;


import com.tsystems.javaschool.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Arrays;
import java.util.HashSet;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    @Serial
    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true,
                true, true, new HashSet<GrantedAuthority>(Arrays.asList(user.getRole())));
        this.user = user;
    }

    public int getId() {
        return user.id();
    }

    public void update(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
