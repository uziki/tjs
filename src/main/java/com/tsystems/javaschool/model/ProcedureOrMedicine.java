package com.tsystems.javaschool.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = ProcedureOrMedicine.BY_NAME_AND_TYPE, query = "SELECT p FROM ProcedureOrMedicine p WHERE p.name=:name AND p.prescriptionType=:type")
})

@Entity
@Table(name = "proc_or_meds")
public class ProcedureOrMedicine extends AbstractBaseEntity {
    public static final String BY_NAME_AND_TYPE = "ProcedureOrMedicine.getByNameAndType";

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
        this(null, name, prescriptionType);
    }

    public ProcedureOrMedicine(Integer id, String name, PrescriptionType prescriptionType) {
        super(id);
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
