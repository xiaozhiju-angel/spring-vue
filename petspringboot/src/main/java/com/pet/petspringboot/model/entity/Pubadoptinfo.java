package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Pubadoptinfo {
    private int id;
    private String userName;
    private String userTel;
    private String userEmail;
    private String adoptType;
    private String abandonpetName;
    private String abandonpetSex;
    private String abandonpetType;
    private String abandonpetImg;
    private String abandonReason;
    private Integer petId;
    private Integer userId;
    private Date publishTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "abandonpet_name")
    public String getAbandonpetName() {
        return abandonpetName;
    }

    public void setAbandonpetName(String abandonpetName) {
        this.abandonpetName = abandonpetName;
    }

    @Basic
    @Column(name = "abandonpet_sex")
    public String getAbandonpetSex() {
        return abandonpetSex;
    }

    public void setAbandonpetSex(String abandonpetSex) {
        this.abandonpetSex = abandonpetSex;
    }

    @Basic
    @Column(name = "abandonpet_type")
    public String getAbandonpetType() {
        return abandonpetType;
    }

    public void setAbandonpetType(String abandonpetType) {
        this.abandonpetType = abandonpetType;
    }

    @Basic
    @Column(name = "abandonpet_img")
    public String getAbandonpetImg() {
        return abandonpetImg;
    }

    public void setAbandonpetImg(String abandonpetImg) {
        this.abandonpetImg = abandonpetImg;
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
    @Column(name = "publish_time")
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pubadoptinfo that = (Pubadoptinfo) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userTel, that.userTel) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(adoptType, that.adoptType) &&
                Objects.equals(abandonpetName, that.abandonpetName) &&
                Objects.equals(abandonpetSex, that.abandonpetSex) &&
                Objects.equals(abandonpetType, that.abandonpetType) &&
                Objects.equals(abandonpetImg, that.abandonpetImg) &&
                Objects.equals(abandonReason, that.abandonReason) &&
                Objects.equals(petId, that.petId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(publishTime, that.publishTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petId, userId, userName, userTel, userEmail, adoptType, abandonpetName, abandonpetSex, abandonpetType, abandonpetImg, abandonReason, publishTime);
    }
}
