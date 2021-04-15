package com.tsystems.javaschool.model;

import java.util.List;

public class User {

    private String login;

    private String password;

    private String name;

    private Role role;

    private List<Patient> patients; //protected?

    public User() {}

    public User(String login, String password, String name, Role role, List<Patient> patients) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
        this.patients = patients;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
