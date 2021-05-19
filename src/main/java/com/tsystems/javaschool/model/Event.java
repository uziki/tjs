package com.tsystems.javaschool.model;

import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Event.DELETE, query = "DELETE FROM Event e WHERE e.id=:id"),
        @NamedQuery(name = Event.ALL_SORTED, query = "SELECT e FROM Event e ORDER BY e.dateTime ASC"),
        @NamedQuery(name = Event.GET_BY_PRESCRIPTION, query = "SELECT e FROM Event e WHERE e.prescription.id=:prescriptionId"),
        @NamedQuery(name = Event.GET_BETWEEN_DATE, query = "SELECT e FROM Event e WHERE e.dateTime >= :startDateTime " +
                "AND e.dateTime <= :endDateTime AND e.eventStatus='STATUS_PLANNED' ORDER BY e.dateTime ASC"),
        @NamedQuery(name = Event.FIND_BY_NAME, query = "SELECT e FROM Event e WHERE e.patient.name LIKE :name")
})
@Entity
@Table(name = "events")
public class Event extends AbstractBaseEntity {

    public static final String DELETE = "Event.delete";
    public static final String ALL_SORTED = "Event.getAllSorted";
    public static final String GET_BY_PRESCRIPTION = "Event.getByPrescription";
    public static final String GET_BETWEEN_DATE = "Event.getForToday";
    public static final String FIND_BY_NAME = "Event.findByName";

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Patient patient;

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull
    private EventStatus eventStatus;

    @OneToOne
    @JoinColumn(name = "proc_or_meds_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ProcedureOrMedicine procedureOrMedicine;

    @Column(name = "message")
    private String message;

    @Column(name = "dose", nullable = false)
    private int dose;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prescription_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Prescription prescription;

    public Event() {
    }

    public Event(Prescription prescription, LocalDateTime localDateTime) {
        this.prescription = prescription;
        this.patient = prescription.getPatient();
        this.dateTime = localDateTime;
        this.eventStatus = EventStatus.STATUS_PLANNED;
        this.procedureOrMedicine = prescription.getProcedureOrMedicine();
        this.dose = prescription.getDose();
    }

    public Event(Patient patient, LocalDateTime dateTime, String procedureOrMedicineName, PrescriptionType type, int dose) {
        this.patient = patient;
        this.dateTime = dateTime;
        this.eventStatus = EventStatus.STATUS_PLANNED;
        this.procedureOrMedicine = new ProcedureOrMedicine(procedureOrMedicineName, type);
        this.dose = dose;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public ProcedureOrMedicine getProcedureOrMedicine() {
        return procedureOrMedicine;
    }

    public void setProcedureOrMedicine(ProcedureOrMedicine procedureOrMedicine) {
        this.procedureOrMedicine = procedureOrMedicine;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
