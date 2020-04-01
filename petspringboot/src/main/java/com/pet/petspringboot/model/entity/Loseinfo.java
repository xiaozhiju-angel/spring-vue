package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Loseinfo {
    private int id;
    private String losepetName;
    private String losepetSex;
    private String losepetType;
    private String losepetImg;
    private String losepetTel;
    private String losepetEmail;
    private String losepetPlace;
    private String losepetDetail;
    private String losepetuserName;
    private Integer petId;
    private Integer userId;
    private Date losepetTime;
    private Date losepubTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "losepet_name")
    public String getLosepetName() {
        return losepetName;
    }

    public void setLosepetName(String losepetName) {
        this.losepetName = losepetName;
    }

    @Basic
    @Column(name = "losepet_sex")
    public String getLosepetSex() {
        return losepetSex;
    }

    public void setLosepetSex(String losepetSex) {
        this.losepetSex = losepetSex;
    }

    @Basic
    @Column(name = "losepet_type")
    public String getLosepetType() {
        return losepetType;
    }

    public void setLosepetType(String losepetType) {
        this.losepetType = losepetType;
    }

    @Basic
    @Column(name = "losepet_img")
    public String getLosepetImg() {
        return losepetImg;
    }

    public void setLosepetImg(String losepetImg) {
        this.losepetImg = losepetImg;
    }

    @Basic
    @Column(name = "losepet_tel")
    public String getLosepetTel() {
        return losepetTel;
    }

    public void setLosepetTel(String losepetTel) {
        this.losepetTel = losepetTel;
    }

    @Basic
    @Column(name = "losepet_email")
    public String getLosepetEmail() {
        return losepetEmail;
    }

    public void setLosepetEmail(String losepetEmail) {
        this.losepetEmail = losepetEmail;
    }

    @Basic
    @Column(name = "losepet_place")
    public String getLosepetPlace() {
        return losepetPlace;
    }

    public void setLosepetPlace(String losepetPlace) {
        this.losepetPlace = losepetPlace;
    }

    @Basic
    @Column(name = "losepet_detail")
    public String getLosepetDetail() {
        return losepetDetail;
    }

    public void setLosepetDetail(String losepetDetail) {
        this.losepetDetail = losepetDetail;
    }

    @Basic
    @Column(name = "losepetuser_name")
    public String getLosepetuserName() {
        return losepetuserName;
    }

    public void setLosepetuserName(String losepetuserName) {
        this.losepetuserName = losepetuserName;
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
    @Column(name = "losepet_time")
    public Date getLosepetTime() {
        return losepetTime;
    }

    public void setLosepetTime(Date losepetTime) {
        this.losepetTime = losepetTime;
    }

    @Basic
    @Column(name = "losepub_time")
    public Date getLosepubTime() {
        return losepubTime;
    }

    public void setLosepubTime(Date losepubTime) {
        this.losepubTime = losepubTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loseinfo loseinfo = (Loseinfo) o;
        return id == loseinfo.id &&
                Objects.equals(losepetName, loseinfo.losepetName) &&
                Objects.equals(losepetSex, loseinfo.losepetSex) &&
                Objects.equals(losepetType, loseinfo.losepetType) &&
                Objects.equals(losepetImg, loseinfo.losepetImg) &&
                Objects.equals(losepetTel, loseinfo.losepetTel) &&
                Objects.equals(losepetEmail, loseinfo.losepetEmail) &&
                Objects.equals(losepetPlace, loseinfo.losepetPlace) &&
                Objects.equals(losepetDetail, loseinfo.losepetDetail) &&
                Objects.equals(losepetuserName, loseinfo.losepetuserName) &&
                Objects.equals(petId, loseinfo.petId) &&
                Objects.equals(userId, loseinfo.userId) &&
                Objects.equals(losepetTime, loseinfo.losepetTime) &&
                Objects.equals(losepubTime, loseinfo.losepubTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petId, userId, losepetName, losepetSex, losepetType, losepetImg, losepetuserName, losepetTel, losepetEmail, losepetTime, losepetPlace, losepetDetail, losepubTime);
    }
}
