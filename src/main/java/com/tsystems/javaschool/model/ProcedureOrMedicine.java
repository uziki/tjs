package com.tsystems.javaschool.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "proc_or_meds")
public class ProcedureOrMedicine extends AbstractBaseEntity{

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull
    private PrescriptionType prescriptionType;

    public ProcedureOrMedicine() {}

    public ProcedureOrMedicine(String name, PrescriptionType prescriptionType) {
        this.name = name;
        this.prescriptionType = prescriptionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PrescriptionType getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(PrescriptionType prescriptionType) {
        this.prescriptionType = prescriptionType;
    }
}
