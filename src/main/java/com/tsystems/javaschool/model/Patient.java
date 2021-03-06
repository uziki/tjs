package com.tsystems.javaschool.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Patient.ALL_SORTED, query = "SELECT p FROM Patient p ORDER BY p.name")
})
@Entity
@Table(name = "patients")
public class Patient extends AbstractBaseEntity {

    public static final String ALL_SORTED = "Patient.getAllSorted";

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "diagnosis")
    private String diagnosis;

    @NotBlank
    @Column(name = "insurance", nullable = false)
    private String insuranceNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User doctor;

    @Column(name = "ill", nullable = false, columnDefinition = "bool default true")
    private boolean ill = true;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;


    public Patient() {
    }

    public Patient(Patient p) {
        this(p.getId(), p.getName(), p.getDiagnosis(), p.getInsuranceNumber(), p.isIll(), p.getDoctor());
        this.prescriptions = p.getPrescriptions();
    }

    public Patient(String name, String diagnosis, String insuranceNumber, boolean ill) {
        this(null, name, diagnosis, insuranceNumber, ill, null);
    }

    public Patient(String name, String diagnosis, String insuranceNumber, boolean ill, User doctor) {
        this(null, name, diagnosis, insuranceNumber, ill, doctor);
    }

    public Patient(Integer id, String name, String diagnosis, String insuranceNumber, boolean ill, User doctor) {
        super(id);
        this.name = name;
        this.diagnosis = diagnosis;
        this.insuranceNumber = insuranceNumber;
        this.ill = ill;
        this.doctor = doctor;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public boolean isIll() {
        return ill;
    }

    public void setIll(boolean ill) {
        this.ill = ill;
    }

    public String getDoctorName() {
        return doctor.getName();
    }
}
