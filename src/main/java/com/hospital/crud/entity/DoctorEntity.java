package com.hospital.crud.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doctor")
public class DoctorEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "date_birth")
    private Date dateBirth;
    @Column(name = "profile_photo")
    private String profilePhoto;
    @Column(name = "public_id_cloudinary")
    private String publicIdCloudinary;
    @ManyToOne
    @JoinColumn(name="id_hospital", nullable=false)
    private HospitalEntity hospital;

    public DoctorEntity(String name, String lastName, String address, Date dateBirth, String profilePhoto, HospitalEntity hospital) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.dateBirth = dateBirth;
        this.profilePhoto = profilePhoto;
        this.hospital = hospital;
    }

    public DoctorEntity() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getPublicIdCloudinary() {
        return publicIdCloudinary;
    }

    public void setPublicIdCloudinary(String publicIdCloudinary) {
        this.publicIdCloudinary = publicIdCloudinary;
    }

    public HospitalEntity getHospital() {
        return hospital;
    }

    public void setHospital(HospitalEntity hospital) {
        this.hospital = hospital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
