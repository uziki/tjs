package com.tsystems.javaschool.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

import static com.tsystems.javaschool.util.PrescriptionUtil.DAYS;

@NamedQueries({
        @NamedQuery(name = Prescription.DELETE, query = "DELETE FROM Prescription p WHERE p.id=:id"),
        @NamedQuery(name = Prescription.ALL_WITH_ID, query = "SELECT p FROM Prescription p " +
                "WHERE p.patient.id=:patientId ORDER BY p.active DESC"),
        @NamedQuery(name = Prescription.ALL, query = "SELECT p FROM Prescription p ORDER BY p.active"),
        @NamedQuery(name = Prescription.GET_WITH_ID, query = "SELECT p FROM Prescription p " +
                "WHERE p.id=:id AND p.patient.id=:patientId")
})
@Entity
@Table(name = "prescriptions")
public class Prescription extends AbstractBaseEntity {

    public static final String DELETE = "Prescription.delete";
    public static final String ALL_WITH_ID = "Prescriptions.getAllWithId";
    public static final String ALL = "Prescription.getAll";
    public static final String GET_WITH_ID = "Prescription.getWithId";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User doctor;

    @NotBlank
    @Column(name = "time_pattern", nullable = false)
    private String timePattern;

    @Column(name = "time_period", nullable = false)
    @NotNull
    private int timePeriod;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proc_or_meds_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ProcedureOrMedicine procedureOrMedicine;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<Event> eventList;

    @Column(name = "dose")
    private int dose = 0;

    @Column(name = "active", nullable = false, columnDefinition = "bool default true")
    private boolean active;

    public Prescription() {
    }

    public Prescription(Patient patient, User doctor, String timePattern, int timePeriod, ProcedureOrMedicine procedureOrMedicine) {
        this.patient = patient;
        this.doctor = doctor;
        this.timePattern = timePattern;
        this.timePeriod = timePeriod;
        this.procedureOrMedicine = procedureOrMedicine;
    }

    public Prescription(Patient patient, User doctor, String timePattern, int timePeriod, ProcedureOrMedicine procedureOrMedicine, int dose) {
        this.patient = patient;
        this.doctor = doctor;
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

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        if (isNew()) {
            return "";
        } else {
            return (dose == 0 ? procedureToString() : medicineToString());
        }
    }

    private String medicineToString() {
        String[] times = timePattern.split(" ");
        int count;
        if (timePattern.length() > 18) {
            count = 3;
        } else if (timePattern.length() > 13) {
            count = 2;
        } else if (timePattern.length() > 8) {
            count = 1;
        } else {
            count = 0;
        }
        StringBuilder sb = new StringBuilder(getProcedureOrMedicine().getName() + " " + getDose() + " шт " + count + " раз(а) в день, в ");
        for (int i = 0; i < 3; i++) {
            if (times[i].length() > 2) {
                sb.append(times[i].substring(2)).append(", ");
            }
        }
        sb.append("курс " + getTimePeriod() + " дней(дня)");
        return sb.toString();
    }

    private String procedureToString() {
        String[] days = timePattern.split(" ");
        StringBuilder sb = new StringBuilder(getProcedureOrMedicine().getName() + " по следующим дням: ");
        for (int i = 0; i < 7; i++) {
            if (days[i].length() > 2) {
                sb.append(DAYS.get(i) + " " + days[i].substring(2) + ", ");
            }
        }
        sb.append("курс (недель): " + getTimePeriod());
        return sb.toString();
    }
}
