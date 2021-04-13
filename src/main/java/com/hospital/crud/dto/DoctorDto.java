package com.hospital.crud.dto;

import com.sun.istack.NotNull;

import java.util.Date;

public class DoctorDto {

    @NotNull
    private String name;
    private String lastName;
    private Date dateBirth;
    private String address;
    private String profilePhoto;
    private String publicIdCloudinary;
    private HospitalDto hospital;
    private int hospitalId;

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

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getPublicIdCloudinary() {
        return publicIdCloudinary;
    }

    public void setPublicIdCloudinary(String publicIdCloudinary) {
        this.publicIdCloudinary = publicIdCloudinary;
    }

    public HospitalDto getHospital() {
        return hospital;
    }

    public void setHospital(HospitalDto hospital) {
        this.hospital = hospital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
