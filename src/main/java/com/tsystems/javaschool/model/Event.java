package com.tsystems.javaschool.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Patient patient;

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "event_status", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "status")
    private EventStatus eventStatus;

    @OneToOne
    @JoinColumn(name = "meds_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ProcedureOrMedicine procedureOrMedicine;

    @NotBlank
    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "dose", nullable = false)
    @NotNull
    private int dose;

    public Event() {}

    public Event(Patient patient, LocalDateTime dateTime, String procedureOrMedicineName, PrescriptionType type, int dose) {
        this.patient = patient;
        this.dateTime = dateTime;
        this.eventStatus = EventStatus.PLANNED;
        this.procedureOrMedicine = new ProcedureOrMedicine(procedureOrMedicineName, type);
        this.dose = dose;
    }
}
