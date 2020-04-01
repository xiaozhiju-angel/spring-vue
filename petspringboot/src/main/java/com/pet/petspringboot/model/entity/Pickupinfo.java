package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Pickupinfo {
    private int id;
    private String pickpetuserName;
    private String pickpetuserTel;
    private String pickpetuserEmail;
    private String pickuserProp;
    private String pickpetSex;
    private String pickpetType;
    private String pickpetImg;
    private String pickpetPlace;
    private String pickpetState;
    private String pickpetDetail;
    private Integer userId;
    private Date pickpetTime;
    private Date pickpubTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pickpetuser_name")
    public String getPickpetuserName() {
        return pickpetuserName;
    }

    public void setPickpetuserName(String pickpetuserName) {
        this.pickpetuserName = pickpetuserName;
    }

    @Basic
    @Column(name = "pickpetuser_tel")
    public String getPickpetuserTel() {
        return pickpetuserTel;
    }

    public void setPickpetuserTel(String pickpetuserTel) {
        this.pickpetuserTel = pickpetuserTel;
    }

    @Basic
    @Column(name = "pickpetuser_email")
    public String getPickpetuserEmail() {
        return pickpetuserEmail;
    }

    public void setPickpetuserEmail(String pickpetuserEmail) {
        this.pickpetuserEmail = pickpetuserEmail;
    }

    @Basic
    @Column(name = "pickpet_sex")
    public String getPickpetSex() {
        return pickpetSex;
    }

    public void setPickpetSex(String pickpetSex) {
        this.pickpetSex = pickpetSex;
    }

    @Basic
    @Column(name = "pickpet_type")
    public String getPickpetType() {
        return pickpetType;
    }

    public void setPickpetType(String pickpetType) {
        this.pickpetType = pickpetType;
    }

    @Basic
    @Column(name = "pickpet_img")
    public String getPickpetImg() {
        return pickpetImg;
    }

    public void setPickpetImg(String pickpetImg) {
        this.pickpetImg = pickpetImg;
    }

    @Basic
    @Column(name = "pickpet_place")
    public String getPickpetPlace() {
        return pickpetPlace;
    }

    public void setPickpetPlace(String pickpetPlace) {
        this.pickpetPlace = pickpetPlace;
    }

    @Basic
    @Column(name = "pickpet_state")
    public String getPickpetState() {
        return pickpetState;
    }

    public void setPickpetState(String pickpetState) {
        this.pickpetState = pickpetState;
    }

    @Basic
    @Column(name = "pickpet_detail")
    public String getPickpetDetail() {
        return pickpetDetail;
    }

    public void setPickpetDetail(String pickpetDetail) {
        this.pickpetDetail = pickpetDetail;
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
    @Column(name = "pickuser_prop")
    public String getPickuserProp() {
        return pickuserProp;
    }

    public void setPickuserProp(String pickuserProp) {
        this.pickuserProp = pickuserProp;
    }

    @Basic
    @Column(name = "pickpet_time")
    public Date getPickpetTime() {
        return pickpetTime;
    }

    public void setPickpetTime(Date pickpetTime) {
        this.pickpetTime = pickpetTime;
    }

    @Basic
    @Column(name = "pickpub_time")
    public Date getPickpubTime() {
        return pickpubTime;
    }

    public void setPickpubTime(Date pickpubTime) {
        this.pickpubTime = pickpubTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pickupinfo that = (Pickupinfo) o;
        return id == that.id &&
                Objects.equals(pickpetuserName, that.pickpetuserName) &&
                Objects.equals(pickpetuserTel, that.pickpetuserTel) &&
                Objects.equals(pickpetuserEmail, that.pickpetuserEmail) &&
                Objects.equals(pickpetSex, that.pickpetSex) &&
                Objects.equals(pickpetType, that.pickpetType) &&
                Objects.equals(pickpetImg, that.pickpetImg) &&
                Objects.equals(pickpetPlace, that.pickpetPlace) &&
                Objects.equals(pickpetState, that.pickpetState) &&
                Objects.equals(pickpetDetail, that.pickpetDetail) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(pickuserProp, that.pickuserProp) &&
                Objects.equals(pickpetTime, that.pickpetTime) &&
                Objects.equals(pickpubTime, that.pickpubTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, pickpetuserName, pickpetuserTel, pickpetuserEmail, pickuserProp, pickpetSex, pickpetType, pickpetImg, pickpetTime, pickpetPlace, pickpetState, pickpetDetail, pickpubTime);
    }
}
