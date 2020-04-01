package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Acceptpet {
    private int acceptId;
    private Integer userId;
    private String userName;
    private String userTel;
    private String userEmail;
    private String adoptType;
    private String petType;
    private Date pubtime;

    @Id
    @Column(name = "accept_id")
    public int getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(int acceptId) {
        this.acceptId = acceptId;
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
    @Column(name = "pet_type")
    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
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
        Acceptpet acceptpet = (Acceptpet) o;
        return acceptId == acceptpet.acceptId &&
                Objects.equals(userId, acceptpet.userId) &&
                Objects.equals(userName, acceptpet.userName) &&
                Objects.equals(userTel, acceptpet.userTel) &&
                Objects.equals(userEmail, acceptpet.userEmail) &&
                Objects.equals(adoptType, acceptpet.adoptType) &&
                Objects.equals(petType, acceptpet.petType) &&
                Objects.equals(pubtime, acceptpet.pubtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acceptId, userId, userName, userTel, userEmail, adoptType, petType, pubtime);
    }
}
