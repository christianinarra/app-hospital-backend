package com.hospital.crud.entity;

import javax.persistence.*;

@Entity
@Table(name = "specialty")
public class SpecialtyEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "avatar_icon")
    private String avatarIcon;
    @Column(name = "public_id_cloudinary")
    private String publicIdCloudinary;

    public SpecialtyEntity(String name, String description, String avatarIcon) {
        this.name = name;
        this.description = description;
        this.avatarIcon = avatarIcon;
    }

    public SpecialtyEntity() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarIcon() {
        return avatarIcon;
    }

    public void setAvatarIcon(String avatarIcon) {
        this.avatarIcon = avatarIcon;
    }

    public String getPublicIdCloudinary() {
        return publicIdCloudinary;
    }

    public void setPublicIdCloudinary(String publicIdCloudinary) {
        this.publicIdCloudinary = publicIdCloudinary;
    }
}
