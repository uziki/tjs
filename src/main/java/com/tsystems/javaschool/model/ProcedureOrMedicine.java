package com.tsystems.javaschool.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = ProcedureOrMedicine.ALL, query = "SELECT p FROM ProcedureOrMedicine p"),
        @NamedQuery(name = ProcedureOrMedicine.BY_NAME_AND_TYPE, query = "SELECT p FROM ProcedureOrMedicine p WHERE p.name=:name AND p.prescriptionType=:type"),
        @NamedQuery(name = ProcedureOrMedicine.DELETE, query = "DELETE FROM ProcedureOrMedicine p WHERE p.id=:id")
})

@Entity
@Table(name = "proc_or_meds")
public class ProcedureOrMedicine extends AbstractBaseEntity {

    public static final String ALL = "ProcedureOrMedicine.getAll";
    public static final String BY_NAME_AND_TYPE = "ProcedureOrMedicine.getByNameAndType";
    public static final String DELETE = "ProcedureOrMedicine.delete";

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull
    private PrescriptionType prescriptionType;

    public ProcedureOrMedicine() {
    }

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
