package com.tsystems.javaschool.util;

import com.tsystems.javaschool.model.Role;
import com.tsystems.javaschool.model.User;

public class UsersUtil {
    public static final User DOCTOR = new User("doctor", "doctor", "DOCTOR" ,Role.DOCTOR);

    public static final User NURSE = new User("nurse", "nurse", "NURSE" ,Role.NURSE);
}
