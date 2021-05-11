package com.tsystems.javaschool.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractBaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @NotBlank
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany
    private List<Patient> patients;

    public User() {}

    public User(String email, String password, String name, Role role, List<Patient> patients) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.patients = patients;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
