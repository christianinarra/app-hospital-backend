package com.hospital.crud.dto;

import com.sun.istack.NotNull;

public class SpetialtyDto {

    @NotNull
    private String name;
    private String description;
    private String avatarIcon;
    private String publicIdCloudinary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicIdCloudinary() {
        return publicIdCloudinary;
    }

    public void setPublicIdCloudinary(String publicIdCloudinary) {
        this.publicIdCloudinary = publicIdCloudinary;
    }

    public String getAvatarIcon() {
        return avatarIcon;
    }

    public void setAvatarIcon(String avatarIcon) {
        this.avatarIcon = avatarIcon;
    }
}
