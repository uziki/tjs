package com.tsystems.javaschool.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    DOCTOR,
    NURSE;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
