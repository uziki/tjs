package com.tsystems.javaschool.to;

public class EventTo {
    private String time;
    private String patient;
    private String eventPrescription;

    public EventTo(String time, String patient, String eventPrescription) {
        this.time = time;
        this.patient = patient;
        this.eventPrescription = eventPrescription;
    }

    public EventTo() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getEventPrescription() {
        return eventPrescription;
    }

    public void setEventPrescription(String eventPrescription) {
        this.eventPrescription = eventPrescription;
    }

    @Override
    public String toString() {
        return "EventTo{" +
                "time='" + time + '\'' +
                ", patient='" + patient + '\'' +
                ", eventPrescription='" + eventPrescription + '\'' +
                '}';
    }
}
