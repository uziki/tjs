package com.tsystems.javaschool.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_DOCTOR,
    ROLE_NURSE;

    @Override
    public String getAuthority() {
        return name();
    }
}
