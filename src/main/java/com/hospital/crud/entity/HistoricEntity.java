package com.hospital.crud.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "historic")
public class HistoricEntity extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "date_visit")
    private Date dateVisit;
    @ManyToOne
    @JoinColumn(name="id_doctor", nullable=false)
    private DoctorEntity doctor;
    @ManyToOne
    @JoinColumn(name="id_patient", nullable=false)
    private PatientEntity patient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(Date dateVisit) {
        this.dateVisit = dateVisit;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }
}
