package com.tsystems.javaschool.util;

import com.tsystems.javaschool.model.Role;
import com.tsystems.javaschool.model.User;

public class UsersUtil {
    public static final User DOCTOR = new User("doctor", "doctor", "DOCTOR" ,Role.ROLE_DOCTOR, null);

    public static final User NURSE = new User("nurse", "nurse", "NURSE" ,Role.ROLE_NURSE, null);
}
