package com.tsystems.javaschool.model;

import jakarta.validation.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "meds")
public class ProcedureOrMedicine extends AbstractBaseEntity{

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "prescriptionstype", joinColumns = @JoinColumn(name = "meds_id"))
    @Column(name = "type")
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
