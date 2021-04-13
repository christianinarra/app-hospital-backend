package com.hospital.crud.dto;

import com.sun.istack.NotNull;

import java.util.Date;

public class PatientDto {

    @NotNull
    private String name;
    private String lastName;
    private String address;
    private Date dateBirth;
    private String profilePhoto;
    private String publicIdCloudinary;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
