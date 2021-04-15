package com.tsystems.javaschool.model;

import java.util.List;

public class Patient {

    private String name;

    private String diagnosis;

    private String insuranceNumber;

    private User doctor;

    private boolean isIll;

    private List<Prescription> prescriptions;


    public Patient() {
    }

    public Patient(String name, String diagnosis, String insuranceNumber, User doctor, boolean isIll) {
        this.name = name;
        this.diagnosis = diagnosis;
        this.insuranceNumber = insuranceNumber;
        this.doctor = doctor;
        this.isIll = isIll;
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
        return isIll;
    }

    public void setIll(boolean ill) {
        isIll = ill;
    }

    public String getDoctorName() {
        return doctor.getName();
    }
}
