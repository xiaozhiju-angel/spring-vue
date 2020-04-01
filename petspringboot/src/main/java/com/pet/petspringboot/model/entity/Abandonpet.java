package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Abandonpet {
    private int abandonId;
    private Integer petId;
    private Integer userId;
    private String userName;
    private String userTel;
    private String userEmail;
    private String adoptType;
    private String petName;
    private String petSex;
    private String petType;
    private String petImg;
    private String abandonReason;
    private Date pubtime;

    @Id
    @Column(name = "abandon_id")
    public int getAbandonId() {
        return abandonId;
    }

    public void setAbandonId(int abandonId) {
        this.abandonId = abandonId;
    }

    @Basic
    @Column(name = "pet_id")
    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_tel")
    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Basic
    @Column(name = "user_email")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "adopt_type")
    public String getAdoptType() {
        return adoptType;
    }

    public void setAdoptType(String adoptType) {
        this.adoptType = adoptType;
    }

    @Basic
    @Column(name = "pet_name")
    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    @Basic
    @Column(name = "pet_sex")
    public String getPetSex() {
        return petSex;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }

    @Basic
    @Column(name = "pet_type")
    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    @Basic
    @Column(name = "pet_img")
    public String getPetImg() {
        return petImg;
    }

    public void setPetImg(String petImg) {
        this.petImg = petImg;
    }

    @Basic
    @Column(name = "abandon_reason")
    public String getAbandonReason() {
        return abandonReason;
    }

    public void setAbandonReason(String abandonReason) {
        this.abandonReason = abandonReason;
    }

    @Basic
    @Column(name = "pubtime")
    public Date getPubtime() {
        return pubtime;
    }

    public void setPubtime(Date pubtime) {
        this.pubtime = pubtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abandonpet that = (Abandonpet) o;
        return abandonId == that.abandonId &&
                Objects.equals(petId, that.petId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userTel, that.userTel) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(adoptType, that.adoptType) &&
                Objects.equals(petName, that.petName) &&
                Objects.equals(petSex, that.petSex) &&
                Objects.equals(petType, that.petType) &&
                Objects.equals(petImg, that.petImg) &&
                Objects.equals(abandonReason, that.abandonReason) &&
                Objects.equals(pubtime, that.pubtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abandonId, petId, userId, userName, userTel, userEmail, adoptType, petName, petSex, petType, petImg, abandonReason, pubtime);
    }
}
