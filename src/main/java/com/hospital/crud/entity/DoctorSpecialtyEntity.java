package com.hospital.crud.entity;

import javax.persistence.*;

@Entity
@Table(name = "doctor_specialty")
public class DoctorSpecialtyEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="id_doctor", nullable=false)
    private DoctorEntity doctor;
    @ManyToOne
    @JoinColumn(name="id_specialty", nullable=false)
    private SpecialtyEntity specialty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public SpecialtyEntity getSpecialty() {
        return specialty;
    }

    public void setSpecialty(SpecialtyEntity specialty) {
        this.specialty = specialty;
    }
}
