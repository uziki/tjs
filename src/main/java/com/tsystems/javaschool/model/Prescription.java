package com.tsystems.javaschool.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "prescriptions")
public class Prescription extends AbstractBaseEntity {

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Patient patient;

    @NotBlank
    @Column(name = "time_pattern", nullable = false)
    private String timePattern;

    @Column(name = "time_period", nullable = false)
    @NotNull
    private int timePeriod;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "meds_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ProcedureOrMedicine procedureOrMedicine;

    @Column(name = "dose", nullable = false)
    @NotNull
    private int dose;

    public Prescription() {}

    public Prescription(Patient patient, String timePattern, int timePeriod, ProcedureOrMedicine procedureOrMedicine) {
        this.patient = patient;
        this.timePattern = timePattern;
        this.timePeriod = timePeriod;
        this.procedureOrMedicine = procedureOrMedicine;
    }

    public Prescription(Patient patient, String timePattern, int timePeriod, ProcedureOrMedicine procedureOrMedicine, int dose) {
        this.patient = patient;
        this.timePattern = timePattern;
        this.timePeriod = timePeriod;
        this.procedureOrMedicine = procedureOrMedicine;
        this.dose = dose;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTimePattern() {
        return timePattern;
    }

    public void setTimePattern(String timePattern) {
        this.timePattern = timePattern;
    }

    public int getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(int timePeriod) {
        this.timePeriod = timePeriod;
    }

    public ProcedureOrMedicine getProcedureOrMedicine() {
        return procedureOrMedicine;
    }

    public void setProcedureOrMedicine(ProcedureOrMedicine procedureOrMedicine) {
        this.procedureOrMedicine = procedureOrMedicine;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    @Override
    public String toString() {
        return (dose == 0 ? getProcedureOrMedicine().getName() + " " + getTimePattern() :
                getProcedureOrMedicine().getName() + " " + getDose() + " шт" + getTimePattern());
    }
}
